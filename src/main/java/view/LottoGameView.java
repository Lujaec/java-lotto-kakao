package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import model.Lotto;
import model.LottoNumber;
import model.LottoRank;
import model.Lottos;

public class LottoGameView {

    private final Scanner sc;

    public LottoGameView() {
        sc = new Scanner(System.in);
    }

    public int requestCost() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = sc.nextLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해야 합니다.");
            return requestCost();
        }
    }

    public int requestManualLottoAmount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = sc.nextLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해야 합니다.");
            return requestManualLottoAmount();
        }
    }

    public List<List<Integer>> requestManualLottoNumbers(int manualLottoAmount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualLottoNumbers = new ArrayList<>();

        for (int i = 0; i < manualLottoAmount; i++) {
            String input = sc.nextLine();
            List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
            manualLottoNumbers.add(numbers);
        }

        return manualLottoNumbers;
    }

    public void displayLottoAmount(int lottoAmount) {
        System.out.println(lottoAmount + "개를 구매했습니다.");
    }

    public void displayLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            Set<LottoNumber> numbers = lotto.getNumbers();
            displayLotto(numbers);
        }
        System.out.println();
    }

    public List<Integer> requestWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = sc.nextLine();

        try {
            return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해야 합니다.");
            return requestWinningNumbers();
        }
    }

    public int requestBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = sc.nextLine();
        System.out.println();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해야 합니다.");
            return requestBonusNumber();
        }
    }

    public void displayStatistics(Map<LottoRank, Integer> lottoRanks) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        List<LottoRank> ranks = Arrays.asList(LottoRank.values());
        Collections.reverse(ranks);

        for (LottoRank lottoRank : ranks) {
            displayStatistic(lottoRanks, lottoRank);
        }
    }

    public void displayProfit(Double profit) {
        System.out.println("총 수익률은 " + profit + "입니다.");
    }

    private static void displayStatistic(Map<LottoRank, Integer> lottoRanks, LottoRank lottoRank) {
        if (lottoRank == LottoRank.NONE) {
            return;
        }

        int count = lottoRanks.getOrDefault(lottoRank, 0);
        System.out.println(
            lottoRank.getMatchCount() + "개 일치" + (lottoRank.isMatchBonus() ? ", 보너스 볼 일치" : "")
                + "(" + lottoRank.getPrize() + "원) - " + count + "개");
    }

    private void displayLotto(Set<LottoNumber> numbers) {
        System.out.println(numbers.stream()
            .map(LottoNumber::getNumber)
            .sorted()
            .map(String::valueOf)
            .collect(Collectors.joining(", ", "[", "]")));
    }
}
