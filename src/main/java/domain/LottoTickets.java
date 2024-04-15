package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTickets {

    private static final int LOTTO_PRICE = 1000;
    private List<LottoTicket> lottoTickets;

    public LottoTickets() {
        this.lottoTickets = new ArrayList<>();
    }

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public Map<LottoPrice, Integer> calculateRank(WinningLotto winningLotto) {
        return lottoTickets.stream()
                .collect(Collectors.toMap(
                        winningLotto::calculatePrize, lottoTicket -> 1, Integer::sum));
    }

    public Integer purchaseAmount() {
        return lottoTickets.size() * LOTTO_PRICE;
    }

    public List<LottoTicket> getLottoTicketList() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public void addTicket(LottoTicket ticket) {
        lottoTickets.add(ticket);
    }

    public LottoTickets joinTickets(LottoTickets other) {
        return new LottoTickets(Stream.of(lottoTickets, other.lottoTickets)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList()));
    }
}
