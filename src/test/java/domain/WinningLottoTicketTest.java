package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class WinningLottoTicketTest {
	@Test
	void 로또_티켓_하나와_보너스_번호_하나가_당첨_번호를_생성할_수_있다() {
		LottoNumber bonusNumber = LottoNumber.of(7);
		LottoTicket winningLottoTicket = LottoTicket.of(List.of(1, 2, 3, 4, 5, 6));

		assertDoesNotThrow(() -> new WinningLottoTicket(winningLottoTicket, bonusNumber));
	}

	@Test
	void 당첨_번호에_보너스_번호가_포함되면_예외를_던진다() {
		LottoNumber bonusNumber = LottoNumber.of(6);
		LottoTicket winningLottoTicket = LottoTicket.of(List.of(1, 2, 3, 4, 5, 6));

		assertThatThrownBy((() -> new WinningLottoTicket(winningLottoTicket, bonusNumber)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 로또_티켓들에대한_당첨_결과를_출력할_수_있다() {
		WinningLottoTicket winningLottoTicket = new WinningLottoTicket(LottoTicket.of(List.of(1, 2, 3, 4, 5, 6)),
			LottoNumber.of(7));
		LottoTicket lottoTicket1 = LottoTicket.of(List.of(1, 2, 3, 4, 5, 6));
		LottoTicket lottoTicket2 = LottoTicket.of(List.of(1, 2, 3, 4, 5, 7));
		LottoTicket lottoTicket3 = LottoTicket.of(List.of(1, 2, 3, 4, 5, 16));
		LottoTicket lottoTicket4 = LottoTicket.of(List.of(1, 2, 3, 4, 15, 16));
		LottoTicket lottoTicket5 = LottoTicket.of(List.of(1, 2, 3, 14, 15, 16));
		LottoTicket lottoTicket6 = LottoTicket.of(List.of(1, 2, 13, 14, 15, 16));
		LottoTicket lottoTicket7 = LottoTicket.of(List.of(1, 12, 13, 14, 15, 16));
		LottoTicket lottoTicket8 = LottoTicket.of(List.of(11, 12, 13, 14, 15, 16));
		LottoTickets lottoTickets = new LottoTickets(
			List.of(lottoTicket1, lottoTicket2, lottoTicket3, lottoTicket4, lottoTicket5, lottoTicket6, lottoTicket7,
				lottoTicket8));

		LottoGameResult lottoGameResult = LottoGameResult.of(
			List.of(
				LottoWinningRank.FIRST_PRIZE,
				LottoWinningRank.SECOND_PRIZE,
				LottoWinningRank.THIRD_PRIZE,
				LottoWinningRank.FOURTH_PRIZE,
				LottoWinningRank.FIFTH_PRIZE,
				LottoWinningRank.LOSE,
				LottoWinningRank.LOSE,
				LottoWinningRank.LOSE
			)
		);

		assertThat(winningLottoTicket.calculateWinningRanksOfTickets(lottoTickets)).isEqualTo(lottoGameResult);
	}
}
