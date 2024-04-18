package com.lotto.view;

import com.lotto.model.LottoNumber;
import com.lotto.model.LottoNumbers;
import com.lotto.model.LottoPlayer;
import com.lotto.model.LottoTicket;

import java.util.List;
import java.util.stream.Collectors;

public class ViewMapper {

    private ViewMapper() {
    }

    public static List<List<Integer>> getLottoTicketList(LottoPlayer lottoPlayer) {
        return lottoPlayer.getLottoTickets().getLottoTickets().stream()
                .map(LottoTicket::getLottoNumbers)
                .map(LottoNumbers::getNumbers)
                .map(lottoNumbers -> lottoNumbers.stream()
                        .map(LottoNumber::getNumber)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
