package lotto;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

	// 로또 티켓에는 LottoNumber가 6개 들어있다.
	// 로또 티켓에는 전부 다른 숫자가 들어있다.

	@Test
	@DisplayName("로또 티켓 생성 확인")
	public void init_lotto_ticket(){
		LottoTicket lottoTicket = new LottoTicket();
		List<LottoNumber> numbers = lottoTicket.getNumbers();
		Assertions.assertThat(numbers.size()).isEqualTo(6);
	}


}
