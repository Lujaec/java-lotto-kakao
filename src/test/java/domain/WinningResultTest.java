package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.Lottos;
import domain.lotto.WinningLotto;

public class WinningResultTest {
	@Test
	@DisplayName("당첨 통계 정보를 계산할 수 있다.")
	void winningResultTest() {
		// given : 사용자가 구매한 Lottos가 존재한다.
		// when : 당첨 통계(각 당첨 개수, 상금)를 계산한다.
		WinningResult result = generateTestWinningResult();

		assertThat(result.getWinningCount(Rank.NONE)).isEqualTo(1);
		assertThat(result.getWinningCount(Rank.FIFTH_WIN)).isEqualTo(0);
		assertThat(result.getWinningCount(Rank.FOURTH_WIN)).isEqualTo(1);
		assertThat(result.getWinningCount(Rank.THIRD_WIN)).isEqualTo(0);
		assertThat(result.getWinningCount(Rank.SECOND_WIN)).isEqualTo(1);
		assertThat(result.getWinningCount(Rank.FOURTH_WIN)).isEqualTo(1);
	}

	@Test
	@DisplayName("당첨 금액을 계산할 수 있다.")
	void winningMoneyResultTest() {
		// given : 사용자가 구매한 Lottos가 존재한다.
		// when : 당첨 통계(각 당첨 개수, 상금)를 계산한다.
		WinningResult result = generateTestWinningResult();

		WinningMoney resultMoney = new WinningMoney(Rank.NONE.getWinningMoney().getMoney());
		resultMoney.add(new WinningMoney(Rank.FOURTH_WIN.getWinningMoney().getMoney()));
		resultMoney.add(new WinningMoney(Rank.SECOND_WIN.getWinningMoney().getMoney()));
		resultMoney.add(new WinningMoney(Rank.FIRST_WIN.getWinningMoney().getMoney()));

		assertThat(result.getWinningMoney()).isEqualTo(resultMoney);
	}

	private WinningResult generateTestWinningResult() {
		// 사용자가 구매한 로또
		Lotto testLotto1 = convertToLotto(1, 2, 3, 4, 5, 6); // 4개 일치
		Lotto testLotto2 = convertToLotto(1, 2, 3, 4, 8, 9); // 6개 일치
		Lotto testLotto3 = convertToLotto(1, 2, 3, 4, 8, 10); // 5개 + 보너스 일치
		Lotto testLotto4 = convertToLotto(11, 12, 13, 14, 15, 16); // 꽝
		Lottos testLottos = new Lottos(List.of(testLotto1, testLotto2, testLotto3, testLotto4));

		// 당첨 로또
		Lotto winnigLotto = convertToLotto(1, 2, 3, 4, 8, 9);
		WinningLotto testWinningLotto = new WinningLotto(winnigLotto, new LottoNumber(10));

		// 결과 계산
		return testLottos.calculateWinningResult(testWinningLotto);
	}

	private Lotto convertToLotto(int ...lottoNumbers) {
		return new Lotto(
			Arrays.stream(lottoNumbers)
				.mapToObj(LottoNumber::new)
				.collect(Collectors.toList()));
	}
}
