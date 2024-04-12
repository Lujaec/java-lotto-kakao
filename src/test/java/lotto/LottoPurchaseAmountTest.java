package lotto;

import lotto.model.LottoPurchaseAmount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoPurchaseAmountTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void 구매_금액은_0_이하가_될_수_없다(int amount) {
        assertThatThrownBy(() -> new LottoPurchaseAmount(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void spent로_구매_금액을_차감할_수_있다() {
        LottoPurchaseAmount lottoPurchaseAmount = new LottoPurchaseAmount(10_000);
        LottoPurchaseAmount leftAmount = lottoPurchaseAmount.spend(1_000);
        assertThat(leftAmount.getPurchaseAmount()).isEqualTo(9_000);
    }

    @Test
    void 가진_금액보다_많은_금액을_소비하려_하면_에러를_리턴한다() {
        LottoPurchaseAmount lottoPurchaseAmount = new LottoPurchaseAmount(10_000);
        assertThatThrownBy(() -> lottoPurchaseAmount.spend(11_000))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
