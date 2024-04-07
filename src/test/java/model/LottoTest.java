package model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
	Lotto lotto;

	@Test
	@DisplayName("로또_길이_테스트")
	void lottoLengthTest() {
		assertThatExceptionOfType(IllegalArgumentException.class)
			.isThrownBy(() ->
				lotto = new Lotto(IntStream.range(1, 10).mapToObj(LottoNumber::getLottoNumberFromPool).collect(Collectors.toList()))
			);
	}

	@Test
	@DisplayName("로또_숫자_중복_테스트")
	void lottoNumberDuplicateTest() {
		assertThatExceptionOfType(IllegalArgumentException.class)
			.isThrownBy(() ->
				lotto = new Lotto(
					IntStream.generate(() -> 5).limit(5).mapToObj(LottoNumber::getLottoNumberFromPool).collect(Collectors.toList()))
			);
	}

	@Test
	@DisplayName("로또_값_한_개_매칭_테스트")
	void lottoNumberMatchingTest() {
		lotto = new Lotto(IntStream.range(1, 7).mapToObj(LottoNumber::getLottoNumberFromPool).collect(Collectors.toList()));

		assertThat(lotto.hasLottoNumber(LottoNumber.getLottoNumberFromPool(1))).isEqualTo(true);
	}

	@Test
	@DisplayName("로또끼리_매칭_테스트")
	void lottoNumbersMatchingTest() {
		lotto = new Lotto(IntStream.range(1, 7).mapToObj(LottoNumber::getLottoNumberFromPool).collect(Collectors.toList()));
		Lotto lotto2 = new Lotto(IntStream.range(1, 7).mapToObj(LottoNumber::getLottoNumberFromPool).collect(Collectors.toList()));

		assertThat(lotto.matchLottoNumbers(lotto2)).isEqualTo(6);
	}
}