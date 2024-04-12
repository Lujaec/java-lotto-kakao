package lotto.model;

import lotto.model.vo.PurchaseMoney;
import lotto.model.vo.RevenueRate;
import lotto.model.vo.WinningMoney;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class WinningStatistics {

    private static final String INVALID_LOTTO_ROUND_RESULT_COUNT_MESSAGE = "당첨 통계는 최소 1개 이상의 로또 결과가 존재해야 합니다.";
    private static final int ZERO = 0;
    private static final int ROUND_SCALE = 2;
    private static final int LOTTO_PRICE = 1000;

    private final Map<LottoRoundResult, Integer> statistics;

    public WinningStatistics(final Map<LottoRoundResult, Integer> result) {
        validateEmptyResult(result);
        this.statistics = result;
    }

    private void validateEmptyResult(final Map<LottoRoundResult, Integer> result) {
        if (result.isEmpty()) {
            throw new IllegalArgumentException(INVALID_LOTTO_ROUND_RESULT_COUNT_MESSAGE);
        }
    }

    public RevenueRate calculateRevenueRate() {
        final WinningMoney totalPrice = calculateTotalPrice();
        final PurchaseMoney purchaseMoney = findPurchaseMoney();
        return new RevenueRate(new BigDecimal(totalPrice.getAmount()).divide(purchaseMoney.toBigDecimal(), ROUND_SCALE, RoundingMode.HALF_UP).doubleValue());
    }

    private WinningMoney calculateTotalPrice() {
        return statistics.entrySet().stream()
                .map(entry -> entry.getKey().getWinningMoney().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(WinningMoney::add)
                .orElse(new WinningMoney(ZERO));
    }

    private PurchaseMoney findPurchaseMoney() {
        return new PurchaseMoney(statistics.values().stream()
                .mapToInt(Integer::intValue)
                .sum() * LOTTO_PRICE);
    }

    public Map<LottoRoundResult, Integer> getStatistics() {
        return statistics;
    }
}
