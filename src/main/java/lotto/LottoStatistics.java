package lotto;

import java.util.HashMap;
import java.util.Map;

public class LottoStatistics {
	private final Map<Rank,Integer> cntByRank = new HashMap<>();

	public void add(Rank rank) {
		cntByRank.put(rank, cntByRank.getOrDefault(rank,0)+1);
	}

	public int countOf(Rank rank) {
		return cntByRank.getOrDefault(rank, 0);
	}

 	public long	totalPrizeMoney() {
		long ret = 0;
		for(Map.Entry<Rank, Integer> map: cntByRank.entrySet()){
			ret += map.getKey().prizeMoney() * map.getValue() ;
		}
		return ret;
	}

	public double profitRate() {
		final int totalCnt = totalCnt();
		if (totalCnt == 0)
			return 0.0;
		return (double) totalPrizeMoney() / (totalCnt * 1000);
	}

	private int totalCnt() {
		int ret = 0;
		for(Map.Entry<Rank, Integer> map: cntByRank.entrySet()){
			ret += map.getValue();
		}
		return ret;
	}
}
