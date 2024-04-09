import dtos.LottoDto;
import dtos.LottoResultDto;
import dtos.WinningResultDto;
import domains.Lotto;
import domains.Lottos;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoConsoleView {
    private final Scanner scanner;

    public LottoConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getMoneyInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public void printIssuedLottos(List<LottoDto> lottoDtos, int numberOfManualLottos) {
        System.out.println("수동으로 "
                + numberOfManualLottos
                + "장, 자동으로 "
                + (lottoDtos.size() - numberOfManualLottos)
                + "개를 구매했습니다.");
        printLottos(lottoDtos);
    }

    private static void printLottos(List<LottoDto> lottoDtos) {
        lottoDtos.stream()
                .map(LottoDto::getNumbers)
                .forEach(System.out::println);
    }

    public List<Integer> getWinningNumbersInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();

        return parseLottoNumberInput(input);
    }

    public int getBonusBallInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    private static String formatWinningResult(WinningResultDto winningResultDto) {
        return winningResultDto.getRegularBallMatch()
                + "개 일치"
                + (winningResultDto.isNeedBonusBallMatch() ? ", 보너스 볼 일치" : "")
                + " ("
                + winningResultDto.getPrize() + "원)- "
                + winningResultDto.getCount()
                + "개";
    }

    public void printResult(LottoResultDto lottoResultDto) {
        List<WinningResultDto> winningResultCount = lottoResultDto.getWinningResultDtos();
        System.out.println("당첨 통계\n" + "---------");
        winningResultCount
                .forEach((winningResultDto) -> System.out.println(formatWinningResult(winningResultDto)));
        System.out.println("총 수익률은 " + String.format("%.2f", lottoResultDto.getRevenueRate()) + "입니다.");
    }

    public int getNumberOfManualInput() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public List<List<Integer>> getManualLottoNumbers(int numberOfManualLottos) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        return IntStream.range(0, numberOfManualLottos)
                .mapToObj((i) -> scanner.nextLine())
                .map(this::parseLottoNumberInput)
                .collect(Collectors.toList());
    }

    private List<Integer> parseLottoNumberInput(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
