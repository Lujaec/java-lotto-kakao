package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RankTest {
	@Test
	@DisplayName("일치하는 숫자에 따라 당첨 정보를 찾을 수 있다.")
	void findRankTest() {
		Rank lose = Rank.findByMatchNumberCount(2, false);
		Rank three = Rank.findByMatchNumberCount(3, false);
		Rank four = Rank.findByMatchNumberCount(4, false);
		Rank six = Rank.findByMatchNumberCount(6, false);

		assertThat(lose).isEqualTo(Rank.NONE);
		assertThat(three).isEqualTo(Rank.FIFTH_WIN);
		assertThat(four).isEqualTo(Rank.FOURTH_WIN);
		assertThat(six).isEqualTo(Rank.FIRST_WIN);
	}

	@Test
	@DisplayName("보너스 번호의 일치 여부에 따라 당첨 결과가 다르다.")
	void bonusRankTest() {
		Rank hasBonus = Rank.findByMatchNumberCount(5, true);
		Rank hasNotBonus = Rank.findByMatchNumberCount(5, false);

		assertThat(hasBonus).isEqualTo(Rank.SECOND_WIN);
		assertThat(hasNotBonus).isEqualTo(Rank.THIRD_WIN);
	}
}
