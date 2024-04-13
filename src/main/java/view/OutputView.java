package view;

import java.util.List;
import java.util.Map;

import model.random.LottoNumbers;
import model.winningLottery.Ranking;


public class OutputView {
    public static void printPurchaseCount(int manulCount, int AutomaticCount) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", manulCount, AutomaticCount);
    }

    public static void printLottoNumberList(List<LottoNumbers> lottoNumberList) {
        lottoNumberList.forEach(
                lottoNumbers -> System.out.println(lottoNumbers.getLottoNumbers())
        );
    }

    public static void printRankingResult(Map<Ranking, Integer> rankingCountMap) {
        System.out.println("\n당첨 통계\n---------");
        rankingCountMap.forEach(
                (key, value) -> System.out.printf("%s (%d원)- %d개\n", key.getCondition(),
                        key.getReward(), value));
    }

    public static void printProfitRateResult(double profitRate) {
        System.out.printf("총 수익률은 %.2f입니다.", profitRate);
    }
}
