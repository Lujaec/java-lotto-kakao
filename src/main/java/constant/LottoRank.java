package constant;

import java.util.Arrays;

public enum LottoRank {

	BLANK(0, 0, false),
	FIFTH(5_000, 3, false),
	FOURTH(50_000, 4, false),
	THIRD(1_500_000, 5, false),
	SECOND(30_000_000, 5, true),
	FIRST(2_000_000_000, 6, false);

	private final int prize;
	private final int matchCount;
	private final boolean bonus;

	LottoRank(int prize, int matchCount, boolean bonus) {
		this.prize = prize;
		this.matchCount = matchCount;
		this.bonus = bonus;
	}

	public int getPrize() {
		return prize;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public boolean getBonus() {
		return bonus;
	}

	public static LottoRank matchRank(int correctNumbersCount, boolean bonus) {
		LottoRank lottoRank = matchNumberCount(correctNumbersCount);
		return matchBonus(lottoRank, bonus);
	}

	private static LottoRank matchNumberCount(int correctNumbersCount) {
		return Arrays.stream(values())
			.filter(lottoRank -> (lottoRank.getMatchCount() == correctNumbersCount))
			.findFirst().orElse(BLANK);
	}

	private static LottoRank matchBonus(LottoRank lottoRank, boolean bonus) {
		if (!bonus) {
			return lottoRank;
		}

		return Arrays.stream(values())
			.filter(rank -> (rank.getBonus() && rank.getPrize() > lottoRank.getPrize() && rank.getMatchCount() == lottoRank.getMatchCount()))
			.findFirst().orElse(lottoRank);
	}
}
