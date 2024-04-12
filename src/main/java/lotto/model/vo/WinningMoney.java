package lotto.model.vo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class WinningMoney {

    private static final int ZERO = 0;
    public static final String INVALID_AMOUNT_MESSAGE = "당첨금은 음수일 수 없습니다.";

    private final BigInteger amount;

    private WinningMoney(final BigInteger amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public WinningMoney(final int amount) {
        this(BigInteger.valueOf(amount));
    }

    public WinningMoney(final String amount) {
        this(new BigInteger(amount));
    }

    private void validateAmount(final BigInteger amount) {
        if (isNegativeAmount(amount)) {
            throw new IllegalArgumentException(INVALID_AMOUNT_MESSAGE);
        }
    }

    private boolean isNegativeAmount(final BigInteger amount) {
        return amount.compareTo(BigInteger.ZERO) < ZERO;
    }

    public WinningMoney add(WinningMoney winningMoney) {
        return new WinningMoney(amount.add(winningMoney.amount));
    }

    public WinningMoney multiply(BigDecimal bigDecimal) {
        return new WinningMoney(amount.multiply(bigDecimal.toBigInteger()));
    }

    public BigInteger getAmount() {
        return amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final WinningMoney that = (WinningMoney) o;
        return Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
