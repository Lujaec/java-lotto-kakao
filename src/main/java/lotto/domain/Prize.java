package lotto.domain;

import java.util.Arrays;

public enum Prize {
    NOTHING(0L, 2, 999),
    FIFTH(5_000L, 3, 5),
    FOURTH(50_000L, 4, 4),
    THIRD(1_500_000L, 5, 3),
    SECOND(30_000_000L, 5, 2),
    FIRST(2_000_000_000L, 6, 1);

    private final Long reward;
    private final int matchCount;
    private final int order;

    Prize(Long reward, int matchCount, int order) {
        this.reward = reward;
        this.matchCount = matchCount;
        this.order = order;
    }

    public Long totalReward(Long count) {
        return this.reward * count;
    }

    public int getOrder() {
        return order;
    }

    public Long getReward() {
        return reward;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public static Prize evaluate(int matchCount, boolean isBonusNumberMatched) {

        if (isBonusNumberMatched && matchCount == SECOND.matchCount) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(prize -> prize.matchCount == matchCount)
                .findAny()
                .orElse(NOTHING);
    }

    @Override
    public String toString() {
        if (this.matchCount <= 2) {
            return "낙첨";
        }

        StringBuilder sb = new StringBuilder(this.matchCount + "개 일치");
        if (this == SECOND) {
            sb.append(", 보너스 볼 일치");
        }
        sb.append("(").append(this.reward).append("원)");

        return sb.toString();
    }
}
