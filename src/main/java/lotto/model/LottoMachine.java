package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private final LottoTicketGeneratable generator;

    public LottoMachine(LottoTicketGeneratable generator) {
        this.generator = generator;
    }

    public LottoTickets buy(Balance balance) {
        long purchaseTicket = calculatePurchaseTicket(balance);

        List<LottoTicket> lottoTicketList = new ArrayList<>();
        for (int i = 0; i < purchaseTicket; i++) {
            lottoTicketList.add(generator.generate());
        }

        return new LottoTickets(lottoTicketList);
    }

    public LottoTickets buy(List<LottoTicket> lottoTickets) {
        return new LottoTickets(lottoTickets);
    }

    private long calculatePurchaseTicket(Balance balance) {
        return balance.getBalance() / LottoTicket.PRICE;
    }
}
