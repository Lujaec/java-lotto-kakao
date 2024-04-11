package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoWinningRankTest {
	@ParameterizedTest
	@CsvSource({
		"6, FIRST_PRIZE",
		"5, THIRD_PRIZE",
		"4, FOURTH_PRIZE",
		"3, FIFTH_PRIZE",
		"2, LOSE",
		"1, LOSE",
		"0, LOSE",
	})
	void 보너스_번호가_불일치하는_경우_로또_순위를_계산(int matchCount, LottoWinningRank lottoWinningRank) {
		assertThat(LottoWinningRank.calculateWinningRank(matchCount, false)).isEqualTo(lottoWinningRank);
	}

	@ParameterizedTest
	@CsvSource({
		"6, FIRST_PRIZE",
		"5, SECOND_PRIZE",
		"4, FOURTH_PRIZE",
		"3, FIFTH_PRIZE",
		"2, LOSE",
		"1, LOSE",
		"0, LOSE",
	})
	void 보너스_번호가_일치하는_경우_로또_순위를_계산(int matchCount, LottoWinningRank lottoWinningRank) {
		assertThat(LottoWinningRank.calculateWinningRank(matchCount, true)).isEqualTo(lottoWinningRank);
	}
}
