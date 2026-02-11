package lotto;

import java.util.HashMap;
import java.util.Map;

public class LottoStatistics {
	private final Map<Rank, Integer> countByRank = new HashMap<>();

	public void add(Rank rank) {
		countByRank.put(rank, countByRank.getOrDefault(rank, 0) + 1);
	}

	public int countOf(Rank rank) {
		return countByRank.getOrDefault(rank, 0);
	}

	public long totalPrizeMoney() {
		long totalPrizeMoney = 0;
		for (Map.Entry<Rank, Integer> rankEntry : countByRank.entrySet()) {
			totalPrizeMoney += rankEntry.getKey().prizeMoney() * rankEntry.getValue();
		}
		return totalPrizeMoney;
	}

	public double profitRate(Money purchaseMoney) {
		if (purchaseMoney.getValue() == 0) {
			return 0.0;
		}
		return (double) totalPrizeMoney() / purchaseMoney.getValue();
	}
}
