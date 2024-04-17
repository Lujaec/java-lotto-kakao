package com.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.lotto.model.LottoNumber;
import com.lotto.model.LottoTicket;
import com.lotto.model.LottoTickets;

public class LottoTicketsTest {
	@Test
	void 구입_금액에_해당하는_로또를_구매할_수_있다() {
		LottoTickets lottoTickets = new LottoTickets(14,
			() -> List.of(1, 2, 3, 4, 5, 6));

		assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(14);
		assertThat(lottoTickets.getLottoTickets()
			.get(0)
			.getLottoNumbers()
			.getLottoNumbersByInt()
			.toArray()).containsExactly(1, 2, 3, 4, 5, 6);
	}

	@Test
	void 수동으로_로또번호를_입력할_수_있다() {
		LottoTickets manualLottoTickets = new LottoTickets();
		manualLottoTickets.add(new LottoTicket(List.of(1, 2, 3, 4, 5, 6)));
		manualLottoTickets.add(new LottoTicket(List.of(7, 8, 9, 10, 11, 12)));
		LottoTickets lottoTickets = new LottoTickets(12,
			() -> List.of(11, 12, 13, 14, 15, 16));

		lottoTickets.addManualLotto(manualLottoTickets);

		assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(14);
		assertThat(lottoTickets.getLottoTickets()
			.get(0)
			.getLottoNumbers()
			.getLottoNumbersByInt()
			.toArray()).containsExactly(1, 2, 3, 4, 5, 6);
		assertThat(lottoTickets.getLottoTickets()
			.get(1)
			.getLottoNumbers()
			.getLottoNumbersByInt()
			.toArray()).containsExactly(7, 8, 9, 10, 11, 12);
	}
}
