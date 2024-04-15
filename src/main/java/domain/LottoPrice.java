package domain;

import java.util.Arrays;

public enum LottoPrice {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NOTHING(0, false, 0);

    private static final int BONUS_RANK = 5;

    private final long count;
    private final boolean bonus;
    private final int price;

    LottoPrice(long count, boolean bonus, int price) {
        this.count = count;
        this.bonus = bonus;
        this.price = price;
    }

    public static LottoPrice matchRank(long count, boolean bonus) {
        if (count == BONUS_RANK) {
            return Arrays.stream(values())
                    .filter(rank -> rank.count == count && rank.bonus == bonus)
                    .findFirst()
                    .orElse(NOTHING);
        }

        return Arrays.stream(values())
                .filter(rank -> rank.count == count)
                .findFirst().orElse(NOTHING);
    }

    public int getPrice() {
        return price;
    }

    public long getCount() {
        return count;
    }

    public boolean isGreaterThan(long count) {
        return this.count > count;
    }
}
