package view;

import constant.Reward;
import model.UserLotto;
import java.util.List;
import java.util.Map;

import static constant.LottoConstants.*;

public class OutputView {

    private OutputView() {
    }

    public static void printPurchaseAmount(int paymentAmount, int purchaseManualAmount) {
        System.out.printf(RESULT_LOTTO_AMOUNT, purchaseManualAmount, paymentAmount - purchaseManualAmount);
    }

    public static void printLottosNumbers(List<UserLotto> userLottos) {
        userLottos.stream()
                .map(UserLotto::getLottoBallNumbers)
                .forEach(System.out::println);
    }

    public static void printResultList(Map<Reward, Integer> result) {
        System.out.println(STATISTICS_MESSAGE);
        System.out.printf(RESULT_LIST_MESSAGE, 3, Reward.FIFTH.getPrice(), result.get(Reward.FIFTH));
        System.out.printf(RESULT_LIST_MESSAGE, 4, Reward.FOURTH.getPrice(), result.get(Reward.FOURTH));
        System.out.printf(RESULT_LIST_MESSAGE, 5, Reward.THIRD.getPrice(), result.get(Reward.THIRD));
        System.out.printf(RESULT_LIST_MESSAGE, 5, Reward.SECOND.getPrice(), result.get(Reward.SECOND));
        System.out.printf(RESULT_LIST_MESSAGE, 6, Reward.FIRST.getPrice(), result.get(Reward.FIRST));
    }

    public static void printResultRate(double rate) {
        System.out.printf(RESULT_REWARD_RATE, rate);
    }
}
