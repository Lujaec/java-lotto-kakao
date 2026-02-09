package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
	@Test
	@DisplayName("구매 가능한 티켓 수량을 반환한다 (1,000의 배수)")
	public void getTicketCount_when_amount_is_multiple_of_1000(){
		int amount = 22000;
		int answerCnt = amount / 1000;

		Money money = new Money(amount);
		Assertions.assertThat(money.toPurchaseCount()).isEqualTo(
			answerCnt
		);
	}

	@Test
	@DisplayName("구매 가능한 티켓 수량을 반환한다 (1,000의 배수가 아닌 금액)")
	public void getTicketCount_when_amount_has_remainder(){
		int amount = 22500;
		int answerCnt = amount / 1000;

		Money money = new Money(amount);
		Assertions.assertThat(money.toPurchaseCount()).isEqualTo(
			answerCnt
		);
	}
}
