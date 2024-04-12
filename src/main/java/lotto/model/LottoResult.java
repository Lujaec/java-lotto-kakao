package lotto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private final List<LottoRank> lottoRanks;
    private final Balance totalBalance;

    public LottoResult(List<LottoRank> lottoRanks, Balance totalBalance) {
        this.lottoRanks = lottoRanks;
        this.totalBalance = totalBalance;
    }

    public long calculateTotalPrize() {
        return lottoRanks.stream()
                .mapToLong(LottoRank::getPrize)
                .sum();
    }

    public float calculateReturnRate() {
        return (float) calculateTotalPrize() / totalBalance.getBalance();
    }

    public Map<LottoRank, Integer> produceStatics() {
        Map<LottoRank, Integer> statistics = createLottoStatics();
        this.lottoRanks.forEach(it -> statistics.computeIfPresent(it, (rank, value) -> value + 1));
        return statistics;
    }

    private Map<LottoRank, Integer> createLottoStatics() {
        Map<LottoRank, Integer> statistics = new HashMap<>();
        statistics.put(LottoRank.FIRST, 0);
        statistics.put(LottoRank.SECOND, 0);
        statistics.put(LottoRank.THIRD, 0);
        statistics.put(LottoRank.FOURTH, 0);
        statistics.put(LottoRank.FIFTH, 0);
        return statistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult that = (LottoResult) o;
        return Objects.equals(lottoRanks, that.lottoRanks) && Objects.equals(totalBalance, that.totalBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoRanks, totalBalance);
    }
}
