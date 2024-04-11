package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoGameResult {

	private final Map<LottoWinningRank, Integer> rankCounts;

	private LottoGameResult(Map<LottoWinningRank, Integer> rankCounts) {
		this.rankCounts = rankCounts;
	}

	public static LottoGameResult of(List<LottoWinningRank> lottoWinningRanks) {
		Map<LottoWinningRank, Integer> rankCounts = new HashMap<>();

		for (LottoWinningRank lottoWinningRank : LottoWinningRank.values()) {
			rankCounts.put(lottoWinningRank, 0);
		}

		for (LottoWinningRank lottoWinningRank : lottoWinningRanks) {
			rankCounts.put(lottoWinningRank, rankCounts.get(lottoWinningRank) + 1);
		}

		return new LottoGameResult(rankCounts);
	}

	public int getCount(LottoWinningRank lottoWinningRank) {
		return rankCounts.get(lottoWinningRank);
	}

	public double calculateYield() {
		int ticketCount = 0;
		int totalPrize = 0;
		for (Map.Entry<LottoWinningRank, Integer> entry : rankCounts.entrySet()) {
			ticketCount += entry.getValue();
			totalPrize += entry.getValue() * entry.getKey().getPrize();
		}
		return totalPrize / (double)(ticketCount * LottoTicket.PRICE);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoGameResult that = (LottoGameResult)o;
		return Objects.equals(rankCounts, that.rankCounts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rankCounts);
	}
}
