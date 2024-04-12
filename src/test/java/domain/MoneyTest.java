package domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Money 단위 테스트")
public class MoneyTest {

    @Test
    void 금액은_0이상() {
        assertThrows(IllegalArgumentException.class, () -> new Money(-12));
    }

    @Test
    void 금액을_나누는_수는_0이_아님() {
        Money money = new Money(1_000);
        assertThrows(IllegalArgumentException.class, () -> money.calculatePurchaseQuantity(new Money(0)));
    }

    @Test
    void 금액이_부족하면_구매불가() {
        Money money = new Money(1_000);
        assertThrows(IllegalArgumentException.class, () -> money.buy(new Money(2_000), new Quantity(3)));
    }
}
