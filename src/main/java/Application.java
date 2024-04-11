import controller.LottoController;
import model.*;
import model.vo.PurchaseAmount;
import view.InputView;
import view.OutputView;
import java.util.List;
import static constant.LottoConstants.LOTTO_PRICE;

public class Application {
    public static void main(String[] args) {
        int totalAmount = inputPurchaseAmount();
        int manualAmount = inputManualPurchaseAmount();
        PurchaseAmount purchaseAmount = new PurchaseAmount(totalAmount - manualAmount, manualAmount);

        List<String> manualUserLottos = generateManualLotto(purchaseAmount.getManualAmount());
        List<UserLotto> userLottos = UserLotto.generateUserLottos(manualUserLottos, purchaseAmount.getAutoAmount());
        LottoController lottoController = new LottoController(userLottos);

        OutputView.printPurchaseAmount(purchaseAmount.getTotalAmount(), purchaseAmount.getManualAmount());
        userLottos = lottoController.getUserLottos();
        OutputView.printLottosNumbers(userLottos);

        WinningLotto winningLotto = settingWinningNumbers();
        Statistics statistics = generateStatistics(lottoController, winningLotto, purchaseAmount);

        OutputView.printResultList(statistics.getResult());
        OutputView.printResultRate(statistics.getStatistics());
    }

    private static int inputManualPurchaseAmount() {
        return InputView.readManualPurchaseAmount();
    }

    private static List<String> generateManualLotto(int manualPurchaseAmount) {
        return InputView.readManualLotto(manualPurchaseAmount);
    }

    private static int inputPurchaseAmount() {
        return InputView.readPaymentAmount()/LOTTO_PRICE;
    }

    private static WinningLotto settingWinningNumbers() {
        String winningNumbersUserInput = InputView.readWinningNumbers();
        Bonus bonus = new Bonus(InputView.readBonusNumber());
        return new WinningLotto(winningNumbersUserInput, bonus);
    }

    private static Statistics generateStatistics(LottoController lottoController, WinningLotto winningLotto, PurchaseAmount purchaseAmount) {
        return new Statistics(lottoController.getUserLottos(), winningLotto, purchaseAmount);
    }
}
