package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketGenerator {
	public static List<LottoTicket> generate(int ticketNumber) {
		if (ticketNumber <= 0)
			throw new IllegalArgumentException("티켓의 개수는 1이상이어야 합니다");

		List<LottoTicket> ret = new ArrayList<>();
		for(int i = 0; i < ticketNumber; i++){
			ret.add(new LottoTicket());
		}
		return ret;
	}
}
