package ui;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.Cash;
import domain.LottoGameResult;
import domain.LottoNumber;
import domain.LottoTicket;
import domain.LottoTickets;
import domain.LottoWinningRank;

public class LottoView {

	private static final Scanner SCANNER = new Scanner(System.in);

	public static Cash getCash() {
		System.out.println("구입금액을 입력해 주세요.");
		return new Cash(Integer.parseInt(SCANNER.nextLine()));
	}

	public static int getCountOfManualTickets() {
		System.out.printf("%n수동으로 구매할 로또 수를 입력해 주세요.%n");

		return Integer.parseInt(SCANNER.nextLine());
	}

	public static List<List<Integer>> getManualLottoTickets(int countOfManualTickets) {
		System.out.printf("%n수동으로 구매할 번호를 입력해 주세요.%n");
		List<List<Integer>> lottoTickets = new ArrayList<>();

		for (int i = 0; i < countOfManualTickets; i++) {
			String line = SCANNER.nextLine();
			List<Integer> numberStrings = Stream.of(line.split(","))
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(Collectors.toUnmodifiableList());
			lottoTickets.add(numberStrings);
		}

		return lottoTickets;
	}

	public static void displayPaidTicketCount(int manualTicketCount, int randomTicketCount) {
		System.out.printf("%n수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", manualTicketCount, randomTicketCount);
	}

	public static void displayLottoTickets(LottoTickets lottoTickets) {
		StringBuilder builder = new StringBuilder();
		for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
			List<LottoNumber> sortedLottoNumbers = lottoTicket.getLottoNumbers();
			Collections.sort(sortedLottoNumbers);
			builder.append(sortedLottoNumbers.toString());
			builder.append("\n");
		}
		System.out.println(builder);
	}

	public static List<Integer> getWinningNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		String line = SCANNER.nextLine();
		List<Integer> numberStrings = Stream.of(line.split(","))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toUnmodifiableList());
		return numberStrings;
	}

	public static LottoNumber getBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return LottoNumber.of(Integer.parseInt(SCANNER.nextLine()));
	}

	public static void displayResult(LottoGameResult lottoGameResult) {
		System.out.println("당첨 통계");
		System.out.println("---------");

		List<LottoWinningRank> ranksExceptLose = getRanksExceptLose();
		StringBuilder builder = new StringBuilder();
		for (LottoWinningRank lottoWinningRank : ranksExceptLose) {
			appendRankResult(builder, lottoGameResult, lottoWinningRank);
		}
		System.out.println(builder);
	}

	private static void appendRankResult(StringBuilder builder, LottoGameResult lottoGameResult,
		LottoWinningRank lottoWinningRank) {
		builder.append(String.format("%d개 일치 ", lottoWinningRank.getMatchCount()));

		if (lottoWinningRank == LottoWinningRank.SECOND_PRIZE) {
			builder.deleteCharAt(builder.length() - 1);
			builder.append(", 보너스 볼 일치");
		}

		builder.append(String.format("(%d원) - %d개", lottoWinningRank.getPrize(), lottoGameResult.getCount(
			lottoWinningRank)));
		builder.append("\n");
	}

	public static void displayYield(double yield) {
		System.out.printf("총 수익률은 %.2f입니다.", yield);

		if (yield < 1.0) {
			System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
		}
	}

	private static List<LottoWinningRank> getRanksExceptLose() {
		return Arrays.stream(LottoWinningRank.values())
			.filter(rank -> rank != LottoWinningRank.LOSE)
			.collect(Collectors.toList());
	}
}