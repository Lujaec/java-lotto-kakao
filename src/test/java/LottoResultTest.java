import model.Cost;
import model.LottoRank;
import model.LottoResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LottoResultTest {
    public static final int EXISTS = 1;

    private static final int TEST_COST_AMOUNT = 1000;

    @Test
    public void calculateStatisticsTest() {
        LottoResult lottoResult = new LottoResult(List.of(LottoRank.FIRST, LottoRank.SECOND, LottoRank.THIRD));

        Map<LottoRank, Long> lottoRankIntegerMap = lottoResult.calculateStatistics();

        assertEquals(lottoRankIntegerMap.get(LottoRank.FIRST), EXISTS);
        assertEquals(lottoRankIntegerMap.get(LottoRank.SECOND), EXISTS);
        assertEquals(lottoRankIntegerMap.get(LottoRank.THIRD), EXISTS);
        assertNull(lottoRankIntegerMap.get(LottoRank.FOURTH));
        assertNull(lottoRankIntegerMap.get(LottoRank.FIFTH));
        assertNull(lottoRankIntegerMap.get(LottoRank.FAIL));
    }

    @ParameterizedTest
    @EnumSource(LottoRank.class)
    public void calculateProfitTest(LottoRank lottoRankForTest) {
        Cost cost = new Cost(TEST_COST_AMOUNT);

        int spentMoney = cost.getSpent();
        LottoResult lottoResult = new LottoResult(List.of(lottoRankForTest));

        Double profit = lottoResult.calculateProfit(cost);

        assertEquals(profit, (double) lottoRankForTest.getPrize() / spentMoney);
    }
}
