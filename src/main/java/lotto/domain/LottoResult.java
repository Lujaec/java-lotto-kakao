package lotto.domain;


import java.util.Map;

public class LottoResult {

    private final Map<Prize, Long> result;

    public LottoResult(Map<Prize, Long> result) {
        this.result = result;
    }

    public Map<Prize, Long> getResult() {
        return Map.copyOf(result);
    }

    public double getProfitRate(LottoPurchaseBudget budget) {
        long profit = result.entrySet().stream()
                .mapToLong(entry -> entry.getKey().totalReward(entry.getValue()))
                .sum();

        return budget.calculateProfitRate(profit);
    }
}
