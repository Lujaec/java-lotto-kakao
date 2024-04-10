package domain;

import java.util.Arrays;

public enum Rank {
	NONE(0, new WinningMoney(0)),
	FIFTH_WIN(3, new WinningMoney(5_000)),
	FOURTH_WIN(4, new WinningMoney(50_000)),
	THIRD_WIN(5, new WinningMoney(1_500_000)),
	SECOND_WIN(5, new WinningMoney(30_000_000)),
	FIRST_WIN(6, new WinningMoney(2_000_000_000));

	private final int matchNumberCount;
	private final WinningMoney winningMoney;

	Rank(int matchNumberCount, WinningMoney winningMoney) {
		this.matchNumberCount = matchNumberCount;
		this.winningMoney = winningMoney;
	}

	public static Rank findByMatchNumberCount(int matchNumberCount, boolean hasBonusNumber) {
		if (matchNumberCount == SECOND_WIN.matchNumberCount && hasBonusNumber) {
			return SECOND_WIN;
		}
		if (matchNumberCount == THIRD_WIN.matchNumberCount) {
			return THIRD_WIN;
		}
		return Arrays.stream(Rank.values())
			.filter(rank -> rank.matchNumberCount == matchNumberCount)
			.findFirst()
			.orElse(NONE);
	}
	
	public int getMatchNumberCount() {
		return matchNumberCount;
	}

	public WinningMoney getWinningMoney() {
		return winningMoney;
	}
}
