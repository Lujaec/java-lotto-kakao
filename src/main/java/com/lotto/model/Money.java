package com.lotto.model;

import java.math.BigInteger;
import java.util.Objects;

public class Money implements Comparable<Money> {

    private final BigInteger amount;

    public Money() {
        this.amount = BigInteger.ZERO;
    }

    public Money(BigInteger amount) {
        validateNotNegative(amount);
        this.amount = amount;
    }

    public static Money valueOf(int amount) {
        return new Money(BigInteger.valueOf(amount));
    }

    @Override
    public int compareTo(Money o) {
        return amount.compareTo(o.amount);
    }

    public boolean isLessThan(Money other) {
        return this.amount.compareTo(other.amount) < 0;
    }

    public Money subtract(Money other) {
        return new Money(this.amount.subtract(other.amount));
    }

    public Money divide(Money other) {
        return new Money(this.amount.divide(other.amount));
    }

    public int intValue() {
        return amount.intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    public Money add(Money money) {
        return new Money(this.amount.add(money.amount));
    }

    private void validateNotNegative(BigInteger amount) {
        if (amount.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("돈은 음수일 수 없습니다.");
        }
    }
}
