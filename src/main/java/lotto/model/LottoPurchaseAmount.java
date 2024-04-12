package lotto.model;

import java.util.Objects;

public class LottoPurchaseAmount {
    private long amount;

    public LottoPurchaseAmount(long amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(long amount) {
        if (amount <= LottoTicket.PRICE) {
            throw new IllegalArgumentException("구매 금액은 최소 1,000원 이상이어야 합니다.");
        }
    }

    public LottoPurchaseAmount spend(long spentAmount) {
        if (amount < spentAmount) {
            throw new IllegalArgumentException("가진 금액보다 많은 금액을 사용할 수 없습니다.");
        }
        return new LottoPurchaseAmount(amount - spentAmount);
    }

    public long getPurchaseAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoPurchaseAmount that = (LottoPurchaseAmount) o;
        return Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
