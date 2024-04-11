package domain;

import java.util.Arrays;

public enum LottoWinningRank {
	LOSE(0, 0),
	FIFTH_PRIZE(3, 5_000),
	FOURTH_PRIZE(4, 50_000),
	THIRD_PRIZE(5, 1_500_000),
	SECOND_PRIZE(5, 30_000_000),
	FIRST_PRIZE(6, 2_000_000_000);

	private final int matchCount;
	private final int prize;

	LottoWinningRank(int matchCount, int prize) {
		this.matchCount = matchCount;
		this.prize = prize;
	}

	public static LottoWinningRank calculateWinningRank(int matchCount, boolean hasBonusNumber) {
		if (hasBonusNumber && matchCount == SECOND_PRIZE.getMatchCount()) {
			return SECOND_PRIZE;
		}
		return Arrays.stream(values())
			.filter(rank -> rank.matchCount == matchCount)
			.findFirst()
			.orElse(LOSE);
	}

	public int getPrize() {
		return prize;
	}

	public int getMatchCount() {
		return matchCount;
	}
}
