package controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Amount;
import model.Ball;
import model.ManualLotto;
import model.calculator.Calculator;
import model.calculator.LottoResult;
import model.random.LottoGenerator;
import model.winningLottery.*;
import view.InputView;
import view.OutputView;

public class LottoController {
    public void run() {
        int budgetInput = InputView.budgetInput();
        int manualCountInput = InputView.manualAmountInput();
        ManualLotto manualLotto = new ManualLotto();

        ManualLotto manualAmount = manualLotto.manualCountInput(budgetInput, manualCountInput);
        Amount automaticAmount = Amount.amountInput(manualAmount.getManualAmount(budgetInput, manualCountInput));

        List<String> manualString = InputView.manualNumbersInput(manualCountInput);

        LottoGenerator lottoGenerator = LottoGenerator.generate(manualString, automaticAmount);

        printPurchaseInformation(lottoGenerator);
        printAutomaticLottoNumbers(lottoGenerator);

        WinningNumbers winningNumbers = WinningNumbers.createBallNumbers(InputView.winningNumbersInput());
        WinningBonusNumber winningBonusNumber = WinningBonusNumber.createWinningBonusNumber(winningNumbers, new Ball(InputView.winningBonusNumberInput()));
        Lottery lottery = new Lottery(winningNumbers, winningBonusNumber);

        List<LottoResult> lottoResultList = generateLottoResults(lottoGenerator);

        Calculator calculator = new Calculator(lottoResultList);
        calculator.saveRanking(lottery);

        printRankingResult(calculator);
        printProfitRateResult(calculator);
    }

    private void printPurchaseInformation(LottoGenerator lottoGenerator) {
        OutputView.printPurchaseCount(lottoGenerator.calculateManualAmount(), lottoGenerator.calculateAutomaticAmount());
    }

    private void printAutomaticLottoNumbers(LottoGenerator lottoGenerator) {
        OutputView.printLottoNumberList(lottoGenerator.getAutomaticLottoNumberList());
    }

    private List<LottoResult> generateLottoResults(LottoGenerator lottoGenerator) {
        return lottoGenerator.getEntireLottoNumberList().stream().map(LottoResult::new).collect(Collectors.toList());
    }

    private void printRankingResult(Calculator calculator) {
        Map<Ranking, Integer> rankingCountMap = calculator.getRankingCountMap();
        OutputView.printRankingResult(rankingCountMap);
    }

    private void printProfitRateResult(Calculator calculator) {
        double profitRate = calculator.calculateProfitRate();
        OutputView.printProfitRateResult(profitRate);
    }
}

