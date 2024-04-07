package view;

import static constant.LottoConstants.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import constant.LottoRank;

public class OutputView {

	public static void printRequestInputLottoNumbers() {
		System.out.println(INPUT_LOTTO_NUMBERS_TO_MANUAL_BUY);
	}

	public static void printNumOfLotto(int numOfManual, int numOfAuto) {
		System.out.printf(LOTTO_MANUAL_AUTO_AMOUNT_MESSAGE, numOfManual, numOfAuto);
	}

	public static void printLottoNumbers(List<List<Integer>> lottolist) {
		lottolist.forEach(System.out::println);
		System.out.println();
	}

	public static void printLottoResult(Map<LottoRank, Integer> lottoResult, double ratio) {
		System.out.println(STATISTICS_MESSAGE);

		Arrays.stream(LottoRank.values())
			.filter(rank -> rank != LottoRank.BLANK)
			.forEach(rank -> printRankAggregation(rank, lottoResult.get(rank)));

		System.out.printf(RESULT_REWARD_RATE, ratio);
	}

	private static void printRankAggregation(LottoRank rankInfo, int numOfWinning) {
		if (rankInfo.getBonus()) {
			System.out.printf(LOTTO_RESULT_WITH_BONUS, rankInfo.getMatchCount(), rankInfo.getPrize(), numOfWinning);
		}
		if (!rankInfo.getBonus()) {
			System.out.printf(LOTTO_RESULT_WITHOUT_BONUS, rankInfo.getMatchCount(), rankInfo.getPrize(), numOfWinning);
		}
	}
}
