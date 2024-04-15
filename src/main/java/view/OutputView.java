package view;

import domain.LottoPrice;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    public void printGameGuide() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printManualCountGuide() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public void printManualLottoGuide() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public void printLottoCount(int manualCount, int randomCount) {
        System.out.println("수동으로 " + manualCount + "장, 자동으로 " + randomCount + "개를 구매했습니다.");
    }

    public void printUserLottos(List<List<Integer>> userLottos) {
        userLottos.forEach(this::printLottoNumbers);
        System.out.println();
    }

    private void printLottoNumbers(List<Integer> lottoNumbers) {
        String lottoNumberString = lottoNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        System.out.println("[" + lottoNumberString + "]");
    }

    public void printWinningNumbersGuide() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printBonusBallGuide() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public void printStatistics(Map<LottoPrice, Integer> lottoPriceCount) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        Arrays.stream(LottoPrice.values())
                .filter(lottoPrice -> lottoPrice.isGreaterThan(0))
                .sorted(Comparator.comparingInt(LottoPrice::getPrice))
                .forEach(lottoPrice -> printStatic(lottoPriceCount, lottoPrice));
    }

    private void printStatic(Map<LottoPrice, Integer> lottoPriceCount, LottoPrice lottoPrice) {
        StringBuilder sb = new StringBuilder(lottoPrice.getCount() + "개 일치");

        if (lottoPrice.equals(LottoPrice.SECOND)) {
            sb.append(", 보너스 볼 일치");
        }

        sb.append("(").append(lottoPrice.getPrice()).append("원) - ")
                .append(lottoPriceCount.getOrDefault(lottoPrice, 0)).append("개");

        System.out.println(sb);
    }

    public void printRevenue(float revenue) {
        System.out.println("총 수익률은 " + DECIMAL_FORMAT.format(revenue) + "입니다.");
    }
}
