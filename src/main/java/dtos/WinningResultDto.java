package dtos;

import domains.WinningResult;

public class WinningResultDto {
    private final int regularBallMatch;

    private final int prize;

    private final boolean needBonusBallMatch;

    private final int count;

    public WinningResultDto(int regularBallMatch, int prize, boolean needBonusBallMatch, int count) {
        this.regularBallMatch = regularBallMatch;
        this.prize = prize;
        this.needBonusBallMatch = needBonusBallMatch;
        this.count = count;
    }

    public static WinningResultDto of(WinningResult winningResult, int count) {
        return new WinningResultDto(
                winningResult.regularBallMatches,
                winningResult.prize,
                winningResult.needBonusBall,
                count
        );
    }

    public int getPrize() {
        return prize;
    }

    public boolean isNeedBonusBallMatch() {
        return needBonusBallMatch;
    }

    public int getCount() {
        return count;
    }

    public int getRegularBallMatch() {
        return regularBallMatch;
    }
}
