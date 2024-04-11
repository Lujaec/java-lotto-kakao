package lotto.view;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.Prize;

public class OutputView {

    public void printLotto(List<Lotto> lotto, int manualCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d장을 구매했습니다.\n", manualCount, lotto.size() - manualCount);
        lotto.forEach(this::printLotto);
        System.out.println();
    }

    private void printLotto(Lotto lotto) {
        System.out.print("[");
        System.out.print(lotto);
        System.out.println("]");
    }

    public void printPrizeHeader() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void printPrizeInfo(Prize prize, int prizeCount) {
        System.out.printf("%d개 일치", prize.getMatchCount());
        if (prize.isBonusMatched()) {
            System.out.print(", 보너스 볼 일");
        }
        System.out.printf(" (%d원) - %d개\n", prize.getReward(), prizeCount);
    }

    public void printRewardRate(double rate) {
        System.out.printf("총 수익률은 %.2f입니다.\n", rate);
    }
}
