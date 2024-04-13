package model;

public class Amount {
    public static final int LOTTO_UNIT_PRICE = 1000;

    private final int amount;

    private Amount(int amount) {
        this.amount = amount;
    }

    public static Amount amountInput(int amount) {
        validateBudget(amount);
        return new Amount(amount);
    }

    private static void validateBudget(int amount) {
        validateProperAmountUnit(amount);
        validatePositiveAmount(amount);
    }

    private static void validateProperAmountUnit(int amount) {
        if (amount > 0 && amount % 1000 != 0) {
            throw new IllegalArgumentException("1000원 단위로 입력해주세요.");
        }
    }

    private static void validatePositiveAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("1000원 이상의 구입금액을 입력해주세요.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
