package view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.LottoNumbers;
import model.winningLottery.Ranking;

import static java.lang.String.valueOf;

public class OutputView {
    public static void printPurchaseCount(int count) {
        System.out.printf("%d 개를 구매했습니다.%n", count);
    }

    public static void printLottoNumberList(List<LottoNumbers> lottoNumberList) {
        lottoNumberList.forEach(lottoNumbers ->
        System.out.println("[" + lottoNumbers.getLottoNumbers().stream()
            .map(lottoNumber -> valueOf(lottoNumber.getBall()))
            .collect(Collectors.joining(",")) + "]"));
    }

    public static void printRankingResult(Map<Ranking, Integer> rankingCountMap) {
        System.out.println("\n당첨 통계\n---------");
        rankingCountMap.forEach(
            (key, value) -> {
                System.out.printf("%d개 일치", key.getWinningMatchCount());
                if (key.isBonusMatch()) {
                    System.out.print(", 보너스 볼 일치");
                }
                System.out.printf("(%d원)- %d개\n",
                    key.getReward(), value);
            });

    }

    public static void printProfitRateResult(double profitRate) {
        System.out.printf("총 수익률은 %.2f입니다.", profitRate);

        if (profitRate < 1) {
            System.out.println(" (기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
    }
}
