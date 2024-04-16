import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Amount;
import model.Ball;
import model.LottoNumbers;
import model.calculator.Calculator;
import model.calculator.LottoResult;
import model.LottoGenerator;
import model.winningLottery.WinMatch;
import model.winningLottery.Ranking;
import model.winningLottery.WinningBonusNumber;
import model.winningLottery.WinningNumbers;
import view.InputView;
import view.OutputView;

import static java.util.stream.Collectors.toList;

public class LottoController {

    public static void run() {
        Amount amount = getAmount();

        LottoGenerator lottoGenerator = generateLottoNumbers(amount);

        WinningNumbers winningNumbers = inputWinningNumbers();

        WinningBonusNumber winningBonusNumber = inputBonusNumber();

        WinMatch winMatch = new WinMatch(winningNumbers, winningBonusNumber);
        Calculator calculator = calculateLottery(winMatch, lottoGenerator);

        printRankingProfit(calculator);
    }

    private static Amount getAmount() {
        int amountInput = InputView.amountInput();
        Amount amount = new Amount(amountInput);
        return amount;
    }

    private static LottoGenerator generateLottoNumbers(Amount amount) {
        int manualCount = InputView.manualCountInput();

        validateStock(amount.getLottoCount(), manualCount);
        List<LottoNumbers> manualLottos = InputView.manualLottoIn(manualCount);

        LottoGenerator lottoGenerator = LottoGenerator.generateAll(amount.getLottoCount(), manualLottos);
        OutputView.printPurchaseCount(lottoGenerator.calculateCount());
        OutputView.printLottoNumberList(lottoGenerator.getLottoNumbers());
        return lottoGenerator;
    }

    private static void validateStock(int wholeCount, int manualCount) {
        if (wholeCount < manualCount){
            throw new IllegalArgumentException("총 구매 가능 갯수를 초과했습니다.");
        }
    }

    private static WinningNumbers inputWinningNumbers() {
        WinningNumbers winningNumbers = WinningNumbers
            .createWinningNumbers(InputView.winningNumbersInput());
        return winningNumbers;
    }

    private static WinningBonusNumber inputBonusNumber() {
        WinningBonusNumber winningBonusNumber = new WinningBonusNumber(Ball.createBall(InputView.winningBonusNumberInput()));
        return winningBonusNumber;
    }

    private static Calculator calculateLottery(WinMatch winMatch, LottoGenerator lottoGenerator) {
        List<LottoResult> lottoResultList = lottoGenerator.getLottoNumbers().stream()
            .map(
                lottoNumbers -> LottoResult.createLottoResult(lottoNumbers, winMatch)
            )
            .collect(toList());
        return Calculator.createCalculator(lottoResultList, winMatch);
    }

    private static void printRankingProfit(Calculator calculator) {
        Map<Ranking, Integer> rankingCountMap = calculator.getRankingCounts();
        OutputView.printRankingResult(rankingCountMap);

        double profitRate = calculator.calculateProfitRate();
        OutputView.printProfitRateResult(profitRate);
    }
}


