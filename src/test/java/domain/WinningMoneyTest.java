package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WinningMoneyTest {
	@Test
	@DisplayName("당첨 상금을 더하는 것이 가능하다.")
	void addWinningAmountTest() {
		WinningMoney winningMoney = new WinningMoney(Rank.FOURTH_WIN.getWinningMoney().getMoney());
		winningMoney.add(new WinningMoney(Rank.THIRD_WIN.getWinningMoney().getMoney()));

		long expectedAmount = Rank.FOURTH_WIN.getWinningMoney().getMoney()
			+ Rank.THIRD_WIN.getWinningMoney().getMoney();
		assertThat(winningMoney.getMoney()).isEqualTo(expectedAmount);
	}
}
