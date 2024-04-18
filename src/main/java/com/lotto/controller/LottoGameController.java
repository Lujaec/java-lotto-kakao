package com.lotto.controller;

import com.lotto.model.*;
import com.lotto.view.LottoGameInputView;
import com.lotto.view.LottoGameOutputView;

import java.util.List;

public class LottoGameController {
    private final LottoGameInputView lottoGameInputView;
    private final LottoGameOutputView lottoGameOutputView;
    private final LottoPlayer lottoPlayer;

    public LottoGameController(LottoGameInputView lottoGameInputView, LottoGameOutputView lottoGameOutputView) {
        this.lottoGameInputView = lottoGameInputView;
        this.lottoGameOutputView = lottoGameOutputView;
        this.lottoPlayer = new LottoPlayer();
    }

    // 목표 : 수동 / 자동을 손쉽게 떼어낼 수 있다.
    public void run() {
        Money money = inputMoney();
        int manualLottoTicketSize = lottoGameInputView.inputManualLottoTicketSize();
        List<List<Integer>> manualLottoTicketNumbers = lottoGameInputView.inputManualLottoTicketNumbers(manualLottoTicketSize);

        LottoGame lottoGame = new LottoGame();
        int autoLottoTicketSize = lottoGame.buyLottos(money, manualLottoTicketNumbers);
        printLottoTickets(manualLottoTicketSize, autoLottoTicketSize);

        TargetLotto targetLotto = inputTargetLotto();
        LottoResults lottoResults = lottoGame.play(targetLotto);
        printStatistics(lottoResults);
    }

    public void printStatistics(LottoResults lottoResults) {
        lottoGameOutputView.printLottoResults(lottoResults);
        lottoGameOutputView.printProfitRate(lottoResults);
    }

    private TargetLotto inputTargetLotto() {
        List<Integer> winningNumbers = lottoGameInputView.inputWinningNumbers();
        int bonusNumber = lottoGameInputView.inputBonusNumber();
        return new TargetLotto(winningNumbers, bonusNumber);
    }

    private Money inputMoney() {
        int moneyInteger = lottoGameInputView.inputMoney();
        return Money.valueOf(moneyInteger);
    }

    private void printLottoTickets(int manualLottoTicketSize, int autoLottoTicketSize) {
        lottoGameOutputView.printLottoTicketSize(manualLottoTicketSize, autoLottoTicketSize);
        lottoGameOutputView.printLottoTicketList(lottoPlayer);
    }
}
