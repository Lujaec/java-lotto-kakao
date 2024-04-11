package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CashTest {
	@Test
	void 캐시가_음수면_예외를_던진다() {
		assertThatExceptionOfType(IllegalArgumentException.class)
			.isThrownBy(() -> new Cash(-1));
	}

	@Test
	void 캐시가_부족하면_예외를_던진다() {
		assertThatExceptionOfType(IllegalArgumentException.class)
			.isThrownBy(() -> new Cash(500));
	}

	@Test
	void 구매_가능한_티켓_개수를_계산한다() {
		Cash cash = new Cash(15100);

		assertThat(cash.getTicketCount()).isEqualTo(15);
	}

	@Test
	void 구매할_티켓_가격의_총합보다_캐시가_적으면_예외를_던진다() {
		Cash cash = new Cash(1000);

		assertThatExceptionOfType(IllegalArgumentException.class)
			.isThrownBy(() -> cash.purchaseLottoTickets(2));
	}

	@Test
	void 티켓을_구매하면_캐시에서_차감된다() {
		Cash cash = new Cash(2000);

		cash.purchaseLottoTickets(1);

		assertThat(cash).isEqualTo(new Cash(1000));
	}
}
