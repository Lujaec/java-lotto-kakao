package lotto.model;

import java.util.Objects;

public class Balance {
    private Long balance;

    public Balance(Long balance) {
        validate(balance);
        this.balance = balance;
    }

    private void validate(Long balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("잔액은 0미만이 될 수 없습니다.");
        }
    }

    public long getBalance() {
        return balance;
    }

    public void pay(long price) {
        validate(balance - price);
        balance -= price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance that = (Balance) o;
        return Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance);
    }
}
