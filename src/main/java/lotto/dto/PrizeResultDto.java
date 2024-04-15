package lotto.dto;

public class PrizeResultDto {
    private final int matchedCount;
    private final boolean isBonusNumberMatched;
    private final Long reward;
    private final Long winningCount;

    public PrizeResultDto(int matchedCount, boolean isBonusNumberMatched, Long reward, Long winningCount) {
        this.matchedCount = matchedCount;
        this.isBonusNumberMatched = isBonusNumberMatched;
        this.reward = reward;
        this.winningCount = winningCount;
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public boolean isBonusNumberMatched() {
        return isBonusNumberMatched;
    }

    public Long getReward() {
        return reward;
    }

    public Long getWinningCount() {
        return winningCount;
    }
}
