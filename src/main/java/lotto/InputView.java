package lotto;

import java.util.ArrayList;
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

	private ArrayList<LottoNumber> parseWinningNumbers(String input) {
		ArrayList<LottoNumber> winningNumbers = new ArrayList<>();
		StringTokenizer stringTokenizer = new StringTokenizer(input, ",");
		while (stringTokenizer.hasMoreTokens()) winningNumbers.add(parseLottoNumber(stringTokenizer.nextToken()));
		validateWinningNumbersCount(winningNumbers);
		validateUniqueNumbers(winningNumbers);
		return winningNumbers;
	}

	private LottoNumber parseLottoNumber(String token) {
		return new LottoNumber(parseInteger(token));
	}

	private void validateWinningNumbersCount(ArrayList<LottoNumber> winningNumbers) {
		if (winningNumbers.size() != 6) {
			throw new IllegalArgumentException("당첨 번호는 6개를 입력해야 합니다.");
		}
	}

	private void validateUniqueNumbers(ArrayList<LottoNumber> winningNumbers) {
		ArrayList<LottoNumber> uniqueNumbers = new ArrayList<>();
		for (LottoNumber winningNumber : winningNumbers) {
			validateNotDuplicated(uniqueNumbers, winningNumber);
			uniqueNumbers.add(winningNumber);
		}
	}

	private void validateNotDuplicated(ArrayList<LottoNumber> uniqueNumbers, LottoNumber winningNumber) {
		if (uniqueNumbers.contains(winningNumber)) {
			throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
		}
	}

	private int parseInteger(String input) {
		try {
			return Integer.parseInt(input.trim());
		} catch (NumberFormatException exception) {
			throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
		}
	}
}
