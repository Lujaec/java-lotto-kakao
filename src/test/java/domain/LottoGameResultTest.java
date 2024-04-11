package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class LottoGameResultTest {
	@Test
	void 수익률을_계산할_수_있다() {
		List<LottoWinningRank> lottoWinningRanks = List.of(
			LottoWinningRank.FIRST_PRIZE,
			LottoWinningRank.SECOND_PRIZE,
			LottoWinningRank.THIRD_PRIZE,
			LottoWinningRank.FOURTH_PRIZE,
			LottoWinningRank.FIFTH_PRIZE
		);

		assertThat(LottoGameResult.of(lottoWinningRanks).calculateYield()).isEqualTo(406311);
	}
}