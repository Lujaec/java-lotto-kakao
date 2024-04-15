package lotto.controller;

import java.util.*;
import java.util.stream.Collectors;

import lotto.domain.*;
import lotto.dto.LottoResultDto;
import lotto.dto.PrizeResultDto;
import lotto.dto.TicketDto;
import lotto.view.LottoView;

import static lotto.domain.Prize.SECOND;

public class LottoController {
    private final LottoView view;

    public LottoController() {
        this.view = new LottoView();
    }

    public void start() {
        LottoPurchaseBudget budget = getBudget();
        int manualLottoCount = getManualLottoCount(budget);

        LottoTickets manualTickets = getManualLottoTickets(manualLottoCount, budget);
        LottoTickets autoTickets = LottoTicketSeller.purchaseAutoLottoTickets(budget);
        LottoTickets totalTickets = manualTickets.addAll(autoTickets);
        view.printTickets(mapToTicketDtos(totalTickets));

        WinningLotto winningLotto = getWinningLotto();
        LottoResult lottoResult = winningLotto.aggregateResult(totalTickets);

        view.printLottoResult(mapToResultDto(lottoResult, budget));
    }

    private LottoPurchaseBudget getBudget() {
        try {
            return new LottoPurchaseBudget(view.getBudget());
        } catch (RuntimeException e) {
            view.printError(e);
            return getBudget();
        }
    }

    private int getManualLottoCount(LottoPurchaseBudget budget) {
        try {
            int manualLottoCount = view.getManualLottoCount();
            budget.validatePurchasable(manualLottoCount);
            return manualLottoCount;
        } catch (RuntimeException e) {
            view.printError(e);
            return getManualLottoCount(budget);
        }
    }

    private LottoTickets getManualLottoTickets(int manualLottoCount, LottoPurchaseBudget budget) {
        try {
            List<List<Integer>> manualLottoNumbers = view.getManualLottoNumbers(manualLottoCount);
            return LottoTicketSeller.purchaseManualLottoTickets(manualLottoNumbers, budget);
        } catch (RuntimeException e) {
            view.printError(e);
            return getManualLottoTickets(manualLottoCount, budget);
        }
    }

    private WinningLotto getWinningLotto() {
        try {
            List<Integer> inputNumbers = view.getNumbers();
            int bonusNumber = view.getBonusNumber();

            List<LottoNumber> lottoNumbers = inputNumbers.stream()
                    .map(LottoNumber::valueOf)
                    .collect(Collectors.toList());

            return new WinningLotto(lottoNumbers, LottoNumber.valueOf(bonusNumber));
        } catch (RuntimeException e) {
            view.printError(e);
            return getWinningLotto();
        }
    }

    private List<TicketDto> mapToTicketDtos(LottoTickets tickets) {
        return tickets.stream()
                .map(LottoTicket::toList)
                .map(LottoController::mapToTicketDto)
                .collect(Collectors.toList());
    }

    private static TicketDto mapToTicketDto(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.collectingAndThen(Collectors.toList(), TicketDto::new));
    }

    private LottoResultDto mapToResultDto(LottoResult lottoResult, LottoPurchaseBudget budget) {
        Map<Prize, Long> result = lottoResult.getResult();

        List<PrizeResultDto> resultDtos = Arrays.stream(Prize.values())
                .filter(prize -> prize != Prize.NOTHING)
                .sorted(Comparator.comparingInt(Prize::getOrder).reversed())
                .map(prize -> mapToPrizeResultDto(prize, result))
                .collect(Collectors.toList());

        return new LottoResultDto(resultDtos, lottoResult.getProfitRate(budget));
    }

    private static PrizeResultDto mapToPrizeResultDto(Prize prize, Map<Prize, Long> result) {
        return new PrizeResultDto(
                prize.getMatchCount(),
                prize.equals(SECOND),
                prize.getReward(),
                result.getOrDefault(prize, 0L));
    }
}
