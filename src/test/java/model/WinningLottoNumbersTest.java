package model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoNumbersTest {
	WinningLottoNumbers winningLottoNumbers;

	@BeforeEach
	void setUp() {
		winningLottoNumbers = new WinningLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), LottoNumber.getLottoNumberFromPool(8));
	}

	@Test
	@DisplayName("보너스_매칭_테스트")
	void hasBonusNumberTest() {
		Lotto lotto = new Lotto(IntStream.range(3, 9).mapToObj(LottoNumber::getLottoNumberFromPool).collect(Collectors.toList()));

		assertThat(winningLottoNumbers.hasBonusNumber(lotto)).isEqualTo(true);
	}

	@Test
	@DisplayName("로또_매칭_테스트")
	void matchNumbersTest() {
		Lotto lotto = new Lotto(IntStream.range(1, 7).mapToObj(LottoNumber::getLottoNumberFromPool).collect(Collectors.toList()));

		assertThat(winningLottoNumbers.matchNumbers(lotto)).isEqualTo(6);
	}
}
