import model.Cost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CostTest {
    @ParameterizedTest
    @ValueSource(ints = {1000, 1500, 1600})
    void calculateLottoAmount(int money) {
        Cost cost = new Cost(money);
        Assertions.assertEquals(1, cost.calculateLottoAmount());
    }

    @Test
    public void cost_ConstructorThrowsExceptionForInvalidCost() {
        int invalidCost = 999;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Cost(invalidCost), "최소 금액 미만일 때 예외가 발생해야 합니다.");
    }

    @Test
    public void getSpent_ReturnsCorrectSpentAmount() {
        Cost cost = new Cost(3000);
        int expectedSpent = 3000;
        Assertions.assertEquals(expectedSpent, cost.getSpent(), "지출한 금액이 올바르게 계산되어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {3000, 3500, 4000, 5000})
    public void calculateAutoLottoAmount(int money) {
        Cost cost = new Cost(money);
        int manualLottoAmount = 3;
        int autoLottoAmount = cost.calculateAutoLottoAmount(manualLottoAmount);
        int totalLottoAmount = cost.calculateLottoAmount();

        Assertions.assertEquals(autoLottoAmount, totalLottoAmount - manualLottoAmount);
    }
}
