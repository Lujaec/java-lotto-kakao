package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputView {
	private final Scanner scanner = new Scanner(System.in);

	public Money readMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return new Money(parseInteger(scanner.nextLine()));
	}

	public LottoTicket readWinningNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return new LottoTicket(parseWinningNumbers(scanner.nextLine()));
	}

	public LottoNumber readBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return new LottoNumber(parseInteger(scanner.nextLine()));
	}

	private List<LottoNumber> parseWinningNumbers(String input) {
		List<LottoNumber> winningNumbers = new ArrayList<>();
		for (String token : input.split(",")) {
			winningNumbers.add(parseLottoNumber(token));
		}
		return winningNumbers;
	}

	private LottoNumber parseLottoNumber(String token) {
		return new LottoNumber(parseInteger(token));
	}

	private int parseInteger(String input) {
		try {
			return Integer.parseInt(input.trim());
		} catch (NumberFormatException exception) {
			throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
		}
	}
}
