package lotto;

import lotto.model.Balance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BalanceTest {
    @ParameterizedTest
    @ValueSource(longs = {-1, -1000})
    void 잔액은_0_미만이_될_수_없다(long amount) {
        assertThatThrownBy(() -> new Balance(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("잔액을 성공적으로 지불한다.")
    @CsvSource(value = {"5000, 1000, 4000", "10000, 5000, 5000", "2000, 2000, 0"})
    void pay(long cash, long price, long left) {
        Balance balance = new Balance(cash);

        balance.pay(price);

        assertThat(balance).isEqualTo(new Balance(left));
    }

    @ParameterizedTest
    @DisplayName("잔액이 모자라면 예외를 던진다.")
    @CsvSource(value = {"5000, 10000", "5000, 5001", "0, 1000"})
    void pay_fail(long cash, long price) {
        Balance balance = new Balance(cash);

        assertThatThrownBy(() -> balance.pay(price))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
