package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LottoTickets {

    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public LottoTickets addAll(LottoTickets otherTickets) {
        List<LottoTicket> totalTickets = new ArrayList<>(tickets);
        totalTickets.addAll(otherTickets.tickets);
        return new LottoTickets(totalTickets);
    }

    public Stream<LottoTicket> stream() {
        return List.copyOf(tickets).stream();
    }

    public int size() {
        return tickets.size();
    }
}
