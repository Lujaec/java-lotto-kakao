package model;

public class Cost {

    private static final int MINIMUM_PRICE = 1000;
    private static final int LOTTO_PRICE = 1000;
    private final int cost;

    public Cost(int cost) {
        validate(cost);
        this.cost = cost;
    }

    private void validate(int cost) {
        if (cost < MINIMUM_PRICE) {
            throw new IllegalArgumentException("로또 금액이 부족합니다. cost: " + cost);
        }
    }

    public int calculateLottoAmount() {
        return cost / LOTTO_PRICE;
    }

    public int getSpent() {
        return calculateLottoAmount() * LOTTO_PRICE;
    }


    public int calculateAutoLottoAmount(int manualLottoAmount) {
        return calculateLottoAmount() - manualLottoAmount;
    }
}
