package domain;

import java.util.Map;

public class WinningResult {
	private final Map<Rank, Integer> winningResult;

	public WinningResult(Map<Rank, Integer> winningResult) {
		this.winningResult = winningResult;
	}

	public EarningRate calculateEarningRate(LottoMoney lottoMoney) {
		return EarningRate.of(lottoMoney, this);
	}

	public int getWinningCount(Rank rank) {
		return this.winningResult.get(rank);
	}

	public WinningMoney getWinningMoney() {
		long result = winningResult.keySet().stream()
			.map(rank -> rank.getWinningMoney().getMoney() * getWinningCount(rank))
			.mapToLong(Long::longValue)
			.sum();
		return new WinningMoney(result);
	}
}
