package model;

public class Amount {
    public static final int LOTTO_UNIT_PRICE = 1000;

    private final int cost;

    public Amount(int cost) {
        validate(cost);
        this.cost = cost;
    }

    public int getLottoCount() {
        return cost / LOTTO_UNIT_PRICE;
    }

    private static void validate(int cost) {
        validatePositiveCost(cost);
        validateProperCostUnit(cost);
    }

    private static void validateProperCostUnit(int cost) {
        if (cost % LOTTO_UNIT_PRICE != 0) {
            throw new IllegalArgumentException("1000원 단위로 입력해주세요.");
        }
    }

    private static void validatePositiveCost(int cost) {
        if (cost <= 0) {
            throw new IllegalArgumentException("금액은 1000원 이상이여야 합니다.");
        }
    }

    public int getCost() {
        return cost;
    }
}
