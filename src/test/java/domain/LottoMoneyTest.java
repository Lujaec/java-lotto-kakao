package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class LottoMoneyTest {
	@Test
	@DisplayName("1000원 단위의 로또 개수를 계산한다.")
	void lottoNumberTest() {
		LottoMoney lottoMoney = new LottoMoney(14000);

		int bought = lottoMoney.calculateLottoCount();

		assertThat(bought).isEqualTo(14);
	}

	@Test
	@DisplayName("1000원으로 나누어떨어지지 않는 부분은 버려진다.")
	void lottoNumberRemainTest() {
		LottoMoney lottoMoney = new LottoMoney(14500);

		int bought = lottoMoney.calculateLottoCount();

		assertThat(bought).isEqualTo(14);
	}

	@Test
	@DisplayName("가용 금액만큼 수동으로 로또를 구매하는 것이 가능하다.")
	void purchaseManualTest() {
		LottoMoney lottoMoney = new LottoMoney(3000);

		assertThatCode(() -> lottoMoney.calculateRemainAfterBuy(3))
			.doesNotThrowAnyException();
	}

	@Test
	@DisplayName("사용 가능 금액 이상의 로또를 구매하면 예외가 발생한다.")
	void cannotPurchaseTest() {
		LottoMoney lottoMoney = new LottoMoney(3000);

		assertThatThrownBy(() -> lottoMoney.calculateRemainAfterBuy(5))
			.isInstanceOf(IllegalStateException.class);
	}

	@Test
	@DisplayName("수동으로 로또 구매 후, 남은 금액 전부로 자동 로또를 구매한다.")
	void purchaseAllTest() {
		LottoMoney startMoney = new LottoMoney(14000);

		LottoMoney remain = startMoney.calculateRemainAfterBuy(3);

		assertThat(remain.calculateLottoCount()).isEqualTo(11);
	}
}
