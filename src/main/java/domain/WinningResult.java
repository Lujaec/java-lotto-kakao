package domain;

import java.util.Arrays;
import java.util.Map;
import java.util.EnumMap;

public class WinningResult {
    private final Map<Rank, Integer> winningResult;
    private final WinningMoney winningMoney;

    public WinningResult(Map<Rank, Long> winningCounts) {
        this.winningResult = new EnumMap<>(Rank.class);
        this.winningMoney = new WinningMoney(Arrays.stream(Rank.values())
                .mapToLong(rank -> {
                    int count = winningCounts.getOrDefault(rank, 0L).intValue();
                    this.winningResult.put(rank, count);
                    return count * rank.getWinningMoney().getMoney();
                }).sum());
    }

    public EarningRate calculateEarningRate(LottoMoney lottoMoney) {
        return EarningRate.of(lottoMoney, this.winningMoney);
    }

    public int getWinningCount(Rank rank) {
        return this.winningResult.get(rank);
    }

    public WinningMoney getWinningMoney() {
        return this.winningMoney;
    }
}
