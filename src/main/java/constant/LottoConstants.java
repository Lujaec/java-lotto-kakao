package constant;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.LottoNumber;

public class LottoConstants {

	public static final int LOTTO_PRICE = 1_000;
	public static final int MAX_LOTTO_NUMBER_SIZE = 6;
	public static final int NUMBER_LOWER_BOUND = 1;
	public static final int NUMBER_UPPER_BOUND = 45;

	public static final List<LottoNumber> lottoNumberPool = IntStream.rangeClosed(NUMBER_LOWER_BOUND, NUMBER_UPPER_BOUND)
		.mapToObj(LottoNumber::new)
		.collect(Collectors.toList());

	public static final String LOTTO_MANUAL_AUTO_AMOUNT_MESSAGE = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
	public static final String STATISTICS_MESSAGE = "\n당첨 통계\n---------";
	public static final String LOTTO_RESULT_WITHOUT_BONUS = "%d개 일치 (%d원) - %d개\n";
	public static final String LOTTO_RESULT_WITH_BONUS = "%d개 일치, 보너스 볼 일치 (%d원) - %d개\n";
	public static final String RESULT_REWARD_RATE = "총 수익률은 %.2f입니다.\n";

	public static final String INPUT_PAYMENT_AMOUNT = "구입금액을 입력해 주세요.";
	public static final String INPUT_NUMBER_OF_LOTTO_TO_MANUAL_BUY = "\n수동으로 구매할 로또 수를 입력해 주세요.";
	public static final String INPUT_LOTTO_NUMBERS_TO_MANUAL_BUY = "\n수동으로 구매할 번호를 입력해 주세요.";
	public static final String INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
	public static final String INPUT_BONUS_NUMBER = "보너스 볼을 입력해주세요.";

	public static final String ERROR_NUMBER_RANGE =
		"[ERROR] 숫자는 " + NUMBER_LOWER_BOUND + "부터 " + NUMBER_UPPER_BOUND + " 사이의 숫자여야 합니다.";
	public static final String ERROR_NUMBER_SIZE = "[ERROR] 로또 숫자는 6자리여야 합니다.";
	public static final String ERROR_DUPLICATE_NUMBER = "[ERROR] 로또 숫자는 중복되어선 안됩니다.";
	public static final String ERROR_BONUS_IN_WINNING_NUMBER = "[ERROR] 로또 당첨 숫자와 보너스 볼은 중복되어선 안됩니다.";
	public static final String ERROR_INPUT_TYPE = "[ERROR] 숫자만 입력해 주세요.";
	public static final String ERROR_MONEY_UNDER_LOTTO_PRICE = "[ERROR] 단 한개의 로또도 구매 할 수 없습니다.";
	public static final String ERROR_NOT_ENOUGH_MONEY = "[ERROR] 돈이 부족합니다";
	public static final String ERROR_CAN_NOT_BUY_MINUS = "[ERROR] 음수개의 로또를 구매 할 수는 없습니다.";

}
