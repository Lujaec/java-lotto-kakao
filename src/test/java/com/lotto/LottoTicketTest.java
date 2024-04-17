package com.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.lotto.model.LottoTicket;

public class LottoTicketTest {
	@Test
	void 로또_번호를_생성할_수_있다() {
		LottoTicket lotto = new LottoTicket(List.of(1, 2, 3, 4, 5, 6));
		assertThat(lotto).isInstanceOf(LottoTicket.class);
	}

	@Test
	void 로또_번호는_6개이다() {
		assertThatThrownBy(() -> new LottoTicket(List.of(1, 2, 3, 4, 5, 6, 7)))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
