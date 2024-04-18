package com.lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets = new ArrayList<>();

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }

    public int size() {
        return lottoTickets.size();
    }

    public List<LottoRank> matchAll(TargetLotto targetLotto) {
        return lottoTickets.stream()
                .map(LottoTicket::getLottoNumbers)
                .map(targetLotto::match)
                .collect(Collectors.toList());
    }

    public void add(LottoTicket lottoTicket) {
        lottoTickets.add(lottoTicket);
    }
}
