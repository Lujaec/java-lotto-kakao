package view;

import static constant.LottoConstants.*;

import java.util.Scanner;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static String readPaymentAmount() {
		System.out.println(INPUT_PAYMENT_AMOUNT);
		return SCANNER.nextLine();
	}

	public static String readNumberOfLottoToBuy() {
		System.out.println(INPUT_NUMBER_OF_LOTTO_TO_MANUAL_BUY);
		return SCANNER.nextLine();
	}

	public static String readLottoNumbers() {
		return SCANNER.nextLine();
	}

	public static String readWinningNumbers() {
		System.out.println(INPUT_WINNING_NUMBERS);
		return SCANNER.nextLine();
	}

	public static String readBonusNumber() {
		System.out.println(INPUT_BONUS_NUMBER);
		return SCANNER.nextLine();
	}
}
