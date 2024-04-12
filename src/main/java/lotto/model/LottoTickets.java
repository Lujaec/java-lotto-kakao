package lotto.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTickets {
    private List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public void addAll(LottoTickets lottoTickets) {
        this.lottoTickets = Stream.concat(this.lottoTickets.stream(), lottoTickets.getLottoTickets().stream())
                .collect(Collectors.toList());
    }

    public int getSize() {
        return lottoTickets.size();
    }

    public LottoResult getWinningResult(LottoWinningNumbers winningNumbers) {
        List<LottoRank> lottoRanks = lottoTickets.stream()
                .map(lottoTicket -> lottoTicket.match(winningNumbers))
                .collect(Collectors.toList());

        return new LottoResult(lottoRanks, new Balance(this.getSize() * LottoTicket.PRICE));
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
