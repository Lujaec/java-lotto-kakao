package lotto.model;

import lotto.model.vo.WinningMoney;

public enum LottoRoundResult {

    BOOM(0),
    FIFTH(5_000),
    FOURTH(50_000),
    THIRD(1_500_000),
    SECOND(30_000_000),
    FIRST(2_000_000_000);

    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;

    private final WinningMoney winningMoney;

    LottoRoundResult(final int winningMoney) {
        this.winningMoney = new WinningMoney(winningMoney);
    }

    public static LottoRoundResult makeRoundResult(final int count, final boolean bonusMatch) {
        if (count == THREE) {
            return LottoRoundResult.FIFTH;
        }
        if (count == FOUR) {
            return LottoRoundResult.FOURTH;
        }
        if (count == FIVE && !bonusMatch) {
            return LottoRoundResult.THIRD;
        }
        if (count == FIVE) {
            return LottoRoundResult.SECOND;
        }
        if (count == SIX) {
            return LottoRoundResult.FIRST;
        }
        return LottoRoundResult.BOOM;
    }

    public WinningMoney getWinningMoney() {
        return winningMoney;
    }
}
