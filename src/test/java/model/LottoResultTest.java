package model;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import constant.LottoRank;

public class LottoResultTest {

	LottoResult lottoResult;

	@BeforeEach
	void setUp() {
		lottoResult = new LottoResult();
	}

	@Test
	@DisplayName("집계_테스트")
	void aggregationTest() {
		lottoResult.updateResult(6, false);
		lottoResult.updateResult(5, true);
		lottoResult.updateResult(5, false);

		Map<LottoRank, Integer> result = lottoResult.getResult();
		System.out.println(result);
		assertThat(result.get(LottoRank.FIRST)).isEqualTo(1);
		assertThat(result.get(LottoRank.SECOND)).isEqualTo(1);
		assertThat(result.get(LottoRank.THIRD)).isEqualTo(1);
	}

	@Test
	@DisplayName("통계_테스트")
	void statisticTest() {
		lottoResult.updateResult(3, false);

		assertThat(lottoResult.getLottoPrize()).isEqualTo(new Money(5000));
	}
}
