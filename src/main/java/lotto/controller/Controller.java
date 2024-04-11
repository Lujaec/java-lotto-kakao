package lotto.controller;

import static lotto.domain.LottoMachine.LOTTO_PRICE;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.Prize;
import lotto.domain.Prizes;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public Controller(InputView inputView, OutputView outputView, LottoMachine lottoMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void run() {
        long expense = inputView.promptExpense();
        int manualCount = inputView.promptManualCount();

        List<Lotto> lotto = buyLotto(expense, manualCount);
        outputView.printLotto(lotto, manualCount);

        int lottoCount = lotto.size();
        WinningLotto winningLotto = publishWinningLotto();

        Prizes prizes = new Prizes(lotto, winningLotto);

        printPrizes(prizes);
        outputView.printRewardRate(prizes.getRewardRate(LOTTO_PRICE * lottoCount));
    }

    private List<Lotto> buyLotto(long expense, int manualCount) {
        if (LOTTO_PRICE * manualCount > expense) {
            throw new IllegalArgumentException("구입 금액보다 많은 로또를 구매할 수 없습니다.");
        }

        List<Lotto> lotto = buyManualLotto(manualCount);
        expense -= LOTTO_PRICE * manualCount;

        lotto.addAll(lottoMachine.issue(expense));
        return lotto;
    }

    private List<Lotto> buyManualLotto(int manualCount) {
        List<List<Integer>> manualNumbers = inputView.promptManualNumbers(manualCount);
        return manualNumbers.stream()
                            .map(Lotto::new)
                            .collect(Collectors.toList());
    }

    private WinningLotto publishWinningLotto() {
        List<Integer> numbers = inputView.promptWinningNumbers();
        Integer bonus = inputView.promptBonusNumber();
        return new WinningLotto(numbers, bonus);
    }

    private void printPrizes(Prizes prizes) {
        outputView.printPrizeHeader();
        Prize.reversedValuesForReward().forEach(prize -> printPrize(prize, prizes));
    }

    private void printPrize(Prize prize, Prizes prizes) {
        int prizeCount = prizes.count(prize);
        outputView.printPrizeInfo(prize, prizeCount);
    }
}
