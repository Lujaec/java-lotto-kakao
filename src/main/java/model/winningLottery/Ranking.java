package model.winningLottery;

import static java.util.Arrays.*;
import static model.winningLottery.BonusMatchStatus.*;

public enum Ranking {
    FIRST(6, IS_UNRELATED, 2000000000),
    SECOND(5, IS_MATCH, 30000000),
    THIRD(5, IS_NOT_MATCH, 1500000),
    FOURTH(4, IS_UNRELATED, 50000),
    FIFTH(3, IS_UNRELATED, 5000),
    NONE(0, IS_UNRELATED, 0),
    ;

    Ranking(int winningMatchCount, BonusMatchStatus bonusMatchStatus, long reward) {
        this.winningMatchCount = winningMatchCount;
        this.bonusMatchStatus = bonusMatchStatus;
        this.reward = reward;
    }

    private final int winningMatchCount;
    private final BonusMatchStatus bonusMatchStatus;
    private final long reward;

    public static Ranking calculateRanking(int winningMatchCount, boolean isBonusMatch) {
        return stream(values())
            .filter(ranking -> ranking.isRanking(winningMatchCount, isBonusMatch))
            .findFirst()
            .orElse(NONE);
    }

    private boolean isRanking(int winningMatchCount, boolean isBonusMatch) {
        return this.winningMatchCount == winningMatchCount && this.bonusMatchStatus.matchBonusNumber(isBonusMatch);
    }

    public boolean isBonusMatch() {
        return this.bonusMatchStatus == IS_MATCH;
    }

    public int getWinningMatchCount() {
        return winningMatchCount;
    }

    public long getReward() {
        return reward;
    }
}
