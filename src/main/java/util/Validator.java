package util;

import static constant.LottoConstants.*;

import java.util.HashSet;
import java.util.List;

import model.Lotto;
import model.LottoNumber;
import model.Money;

public class Validator {
	public static void validateIsNumber(String input) {
		try {
			Integer.parseInt(input);
		} catch (Exception e) {
			throw new IllegalArgumentException(ERROR_INPUT_TYPE);
		}
	}

	public static void validateBonusNotInWinningNumbers(Lotto winningLottoNumbers, LottoNumber bonus) {
		if (winningLottoNumbers.hasLottoNumber(bonus)) {
			throw new IllegalArgumentException(ERROR_BONUS_IN_WINNING_NUMBER);
		}
	}

	public static void validateDuplicateLottoNumber(List<LottoNumber> lottoNumbers) {
		if (new HashSet<>(lottoNumbers).size() != lottoNumbers.size()) {
			throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
		}
	}

	public static void validateLottoNumberSize(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != MAX_LOTTO_NUMBER_SIZE) {
			throw new IllegalArgumentException(ERROR_NUMBER_SIZE);
		}
	}

	public static void validateBuyMoney(Money money, int numOfLottoToBuy) {
		if (money.numOfLottoCanBuy() < numOfLottoToBuy) {
			throw new IllegalArgumentException(ERROR_NOT_ENOUGH_MONEY);
		}
		if (numOfLottoToBuy < 0) {
			throw new IllegalArgumentException(ERROR_CAN_NOT_BUY_MINUS);
		}
	}

	public static void validateCanNotBuyLotto(int input) {
		if (input < LOTTO_PRICE) {
			throw new IllegalArgumentException(ERROR_MONEY_UNDER_LOTTO_PRICE);
		}
	}

	public static void validateLottoNumberRange(int number) {
		if (number < NUMBER_LOWER_BOUND || number > NUMBER_UPPER_BOUND) {
			throw new IllegalArgumentException(ERROR_NUMBER_RANGE);
		}
	}
}
