package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.EarningRate;
import domain.Lotto;
import domain.LottoBall;
import domain.Lottos;
import domain.Rank;
import domain.WinningResult;

public class LottoGameView {
    private final Scanner sc = new Scanner(System.in);
    private static final String LOTTO_SEPARATOR = ", ";

    public int getLottoMoneyInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }

    public int getManualLottoCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }

    public List<Lotto> getManualLottos(int count) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        return IntStream.range(0, count)
            .mapToObj(i -> sc.nextLine())
            .map(numbers -> numbers.split(LOTTO_SEPARATOR))
                    .map(Lotto::parseLotto)
            .collect(Collectors.toList());
    }

    public Lotto getWinningLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return Lotto.parseLotto(sc.nextLine().split(LOTTO_SEPARATOR));
    }

    public int getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }

    private void printLotto(Lotto lotto) {
        System.out.println("["
            + lotto.getLottoNumbers().stream()
            .map(String::valueOf)
            .collect(Collectors.joining(", "))
            + "]");
    }

    public void printPurchasedLottos(int manualCount, Lottos lottos) {
        int autoCount = lottos.getLottoCount() - manualCount;
        System.out.println("\n수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");
        lottos.getLottos().forEach(this::printLotto);
        System.out.println();
    }

    public void printWinningRank(WinningResult winningResult) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        Arrays.stream(Rank.values())
            .forEach(rank -> printDescription(rank, winningResult.getWinningCount(rank)));
    }

    public void printEarningRate(EarningRate earningRate) {
        System.out.printf("총 수익률은 %.2f입니다.\n", earningRate.getEarningRate());
    }

    private void printDescription(Rank rank, int winningCount) {
        if (rank.equals(Rank.MATCH_THREE)) {
            System.out.println("3개 일치 (5000원)- " + winningCount + "개");
        }
        if (rank.equals(Rank.MATCH_FOUR)) {
            System.out.println("4개 일치 (50000원)- " + winningCount + "개");
        }
        if (rank.equals(Rank.MATCH_FIVE)) {
            System.out.println("5개 일치 (1500000원)- " + winningCount + "개");
        }
        if (rank.equals(Rank.MATCH_FIVE_WITH_BONUS)) {
            System.out.println("5개 일치, 보너스 볼 일치(30000000원)- " + winningCount + "개");
        }
        if (rank.equals(Rank.MATCH_SIX)) {
            System.out.println("6개 일치 (2000000000원)- " + winningCount + "개");
        }
    }
}
