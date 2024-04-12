package lotto.controller;

import lotto.model.*;
import lotto.model.dto.LottoResultDto;
import lotto.model.dto.LottoTicketDto;
import lotto.view.Input;
import lotto.view.Output;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    public static void play() {
        LottoPurchaseAmount lottoPurchaseAmount = new LottoPurchaseAmount(Input.getPurchaseAmount());
        int manualPurchaseCount = Input.getManualPurchaseCount();
        validateManualPurchaseCount(lottoPurchaseAmount, manualPurchaseCount);
        LottoTickets lottoTickets = buyLottoTickets(lottoPurchaseAmount, manualPurchaseCount);

        Output.printPurchaseCount(manualPurchaseCount, lottoTickets.getSize() - manualPurchaseCount);
        Output.printLottoTickets(parseDto(lottoTickets));

        LottoTicket winningTicket = parseLottoTicket(Input.getWinningTicket());
        LottoNumber bonusNumber = new LottoNumber(Input.getBonusNumber());
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket(winningTicket, bonusNumber);

        LottoResult lottoResult = lottoTickets.makeWinningResult(winningLottoTicket);
        Output.printResult(new LottoResultDto(lottoResult.calculateReturnRate(lottoPurchaseAmount), lottoResult.makeLottoResultMap()));
    }

    private static void validateManualPurchaseCount(LottoPurchaseAmount lottoPurchaseAmount, int manualPurchaseCount) {
        if (lottoPurchaseAmount.getPurchaseAmount() < (long) manualPurchaseCount * LottoTicket.PRICE) {
            throw new IllegalArgumentException("수동으로 구매할 로또 수가 구매 금액보다 많습니다.");
        }
    }

    private static LottoTicket parseLottoTicket(String lottoTicket) {
        return Arrays.stream(lottoTicket.split(", "))
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoTicket::new));
    }

    private static List<LottoTicketDto> parseDto(LottoTickets lottoTickets) {
        return lottoTickets.getLottoTickets()
                .stream()
                .map(LottoTicketDto::new)
                .collect(Collectors.toList());
    }

    private static LottoTickets buyLottoTickets(LottoPurchaseAmount lottoPurchaseAmount, int manualCount) {
        List<LottoTicket> lottoTicketList = Input.getManualLottoTickets(manualCount).stream()
                .map(LottoController::parseLottoTicket)
                .collect(Collectors.toList());
        LottoTickets manualLottoTickets = new LottoTickets(lottoTicketList);

        LottoPurchaseAmount leftAmount = lottoPurchaseAmount.spend((long) manualCount * LottoTicket.PRICE);
        LottoTickets autoLottoTickets = LottoTicketGenerator.generate(leftAmount);
        return manualLottoTickets.add(autoLottoTickets);
    }
}
