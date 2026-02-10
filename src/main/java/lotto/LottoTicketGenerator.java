package lotto;

import java.util.ArrayList;

public class LottoTicketGenerator {
	public static LottoTickets generate(int ticketCount) {
		validateTicketCount(ticketCount);
		return createLottoTickets(ticketCount);
	}

	private static void validateTicketCount(int ticketCount) {
		if (ticketCount <= 0) {
			throw new IllegalArgumentException("티켓의 개수는 1이상이어야 합니다");
		}
	}

	private static LottoTickets createLottoTickets(int ticketCount) {
		ArrayList<LottoTicket> tickets = new ArrayList<>();
		for (int ticketIndex = 0; ticketIndex < ticketCount; ticketIndex++) {
			tickets.add(new LottoTicket());
		}
		return new LottoTickets(tickets);
	}
}
