package lotto;

import java.util.ArrayList;

public enum Rank {
	FIRST("6개 일치 (2000000000원)", 2000000000L),
	SECOND("5개 일치, 보너스 볼 일치(30000000원)", 30000000L),
	THIRD("5개 일치 (1500000원)", 1500000L),
	FOURTH("4개 일치 (50000원)", 50000L),
	FIFTH("3개 일치 (5000원)", 5000L),
	OTHER("미당첨", 0L);

	private final String description;
	private final long prizeMoney;

	Rank(String description, long prizeMoney) {
		this.description = description;
		this.prizeMoney = prizeMoney;
	}

	public static Rank from(int matchCount, boolean bonusMatched) {
		if (matchCount == 6) return FIRST;
		if (matchCount == 5 && bonusMatched) return SECOND;
		if (matchCount == 5) return THIRD;
		if (matchCount == 4) return FOURTH;
		if (matchCount == 3) return FIFTH;
		return OTHER;
	}

	public static ArrayList<Rank> winningRanks() {
		ArrayList<Rank> winningRanks = new ArrayList<>();
		winningRanks.add(FIFTH);
		winningRanks.add(FOURTH);
		winningRanks.add(THIRD);
		winningRanks.add(SECOND);
		winningRanks.add(FIRST);
		return winningRanks;
	}

	public String description() {
		return description;
	}

	public long prizeMoney() {
		return prizeMoney;
	}
}
