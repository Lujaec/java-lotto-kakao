package com.lotto.model;

import java.util.stream.Stream;

public enum LottoRank {
	FIRST(6, false, 2_000_000_000),
	SECOND(5, true, 30_000_000),
	THIRD(5, false, 1_500_000),
	FOURTH(4, false, 50_000),
	FIFTH(3, false, 5_000),
	FAIL(0, false, 0);
	private final int matchCount;
	private final int prize;
	private final boolean bonus;

	LottoRank(int matchCount, boolean bonus, int prize) {
		this.matchCount = matchCount;
		this.prize = prize;
		this.bonus = bonus;
	}

	public static LottoRank of(int matchCount, boolean bonus) {
		return Stream.of(values())
			.filter(lottoRank -> lottoRank.isEquals(matchCount, bonus))
			.findFirst()
			.orElse(FAIL);
	}

	public int getPrize() {
		return prize;
	}

	public boolean isEquals(int matchCount, boolean bonus) {
		return this.matchCount == matchCount && this.bonus == bonus;
	}

	@Override
	public String toString() {
		String prefix = matchCount + "개 일치";
		if (bonus) {
			prefix += ", 보너스 볼 일치";
		}
		return prefix + " (" + prize + "원)- ";
	}
}
