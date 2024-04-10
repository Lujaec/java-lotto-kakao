package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import domain.EarningRate;
import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.Lottos;
import view.dto.WinningResultDto;

public class LottoGameView {
	private final Scanner sc = new Scanner(System.in);
	private static final String LOTTO_SEPARATOR = ", ";

	public int getLottoMoneyInput() {
		try {
			System.out.println("구입금액을 입력해 주세요.");
			return Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("[ERROR] 숫자를 입력해주세요!");
			return getLottoMoneyInput();
		}
	}

	public int getManualLottoCount() {
		try {
			System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
			return Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("[ERROR] 숫자를 입력해주세요!");
			return getManualLottoCount();
		}
	}
	
	public List<LottoNumber> getManualLottoNumbers() {
		try {
			System.out.println("수동으로 구매할 번호를 입력해 주세요.");
			return Arrays.stream(sc.nextLine().split(LOTTO_SEPARATOR))
				.map(Integer::parseInt)
				.map(LottoNumber::new)
				.collect(Collectors.toList());
		} catch (NumberFormatException e) {
			System.out.println("[ERROR] \", \"로 구분되는 숫자를 입력해주세요!");
			return getWinningLottoNumbers();
		}
	}

	public List<LottoNumber> getWinningLottoNumbers() {
		try {
			System.out.println("지난 주 당첨 번호를 입력해 주세요.");
			return Arrays.stream(sc.nextLine().split(LOTTO_SEPARATOR))
				.map(Integer::parseInt)
				.map(LottoNumber::new)
				.collect(Collectors.toList());
		} catch (NumberFormatException e) {
			System.out.println("[ERROR] \", \"로 구분되는 숫자를 입력해주세요!");
			return getWinningLottoNumbers();
		}
	}

	public int getBonusNumber() {
		try {
			System.out.println("보너스 볼을 입력해 주세요.");
			return Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("[ERROR] 숫자를 입력해주세요!");
			return getBonusNumber();
		}
	}

	public void printPurchasedLottoCount(int manualLottoCount, int autoLottoCount) {
		System.out.printf("수동으로 %d개, 자동으로 %d개를 구매했습니다.\n",
			manualLottoCount, autoLottoCount);
	}

	private void printLotto(Lotto lotto) {
		System.out.println("["
			+ lotto.getLottoNumbers().stream()
			.map(String::valueOf)
			.collect(Collectors.joining(", "))
			+ "]");
	}

	public void printPurchasedLottos(Lottos lottos) {
		lottos.getLottos().forEach(this::printLotto);
		System.out.println();
	}

	public void printWinningRank(List<WinningResultDto> winningResults) {
		System.out.println();
		System.out.println("당첨 통계");
		System.out.println("---------");

		printDescription(winningResults);
	}

	public void printEarningRate(EarningRate earningRate) {
		System.out.printf("총 수익률은 %.2f입니다.\n", earningRate.getEarningRate());
	}

	private void printDescription(List<WinningResultDto> winningResults) {
		winningResults.forEach(winningResult -> {
			if (winningResult.requiresBonusNumber()) {
				System.out.println(winningResult.getRequiredMatchCount() + "개 일치, 보너스 볼 일치 ("
					+ winningResult.getWinningMoney() + "원)- " + winningResult.getMatchCount() + "개");
				return;
			}
			System.out.println(winningResult.getRequiredMatchCount() + "개 일치 ("
				+ winningResult.getWinningMoney() + "원)- " + winningResult.getMatchCount() + "개");
		});
	}
}
