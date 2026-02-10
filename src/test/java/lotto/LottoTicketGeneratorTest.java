package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketGenerator {
	@Test
	@DisplayName("n개의 티켓을 생성해서 List로 반환한다")
	public void generate(){
		final int TICKET_NUMBER = 17;

		LottoTicketGenerator lottoTicketGenerator = new LottoTicketGenerator(TICKET_NUMBER);
	}
}
