package domain;

import java.util.Objects;

public class Money {

    private final long value;

    public Money(long value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
        }
    }

    public Money buy(Money price, Quantity quantity) {
        long used = price.value * quantity.getValue();
        validateRemainMoney(used);

        return new Money(this.value - used);
    }

    private void validateRemainMoney(long need) {
        if (this.value < need) {
            throw new IllegalArgumentException("금액이 부족합니다.");
        }
    }

    public Quantity calculatePurchaseQuantity(Money price) {
        validatePurchasePrice(price);
        return Quantity.subtract(this.value, price.value);
    }

    private void validatePurchasePrice(Money price) {
        if (price.value == 0) {
            throw new IllegalArgumentException("구매 금액은 0이 될 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
