package model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
	Money money;

	@BeforeEach
	void setUp() {
		money = new Money(1000);
	}

	@Test
	@DisplayName("몇개_살_수_있는지_테스트")
	void numOfLottoCanBuyTest() {
		assertThat(money.numOfLottoCanBuy()).isEqualTo(1);
	}

	@Test
	@DisplayName("수익률_테스트")
	void calculateReturnRatioTest() {
		assertThat(money.calculateReturnRatio(1)).isEqualTo(1);
	}
}