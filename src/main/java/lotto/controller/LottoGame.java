package lotto.controller;

import lotto.model.*;

import java.util.List;

public class LottoGame {
    private final LottoMachine lottoMachine;

    public LottoGame(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public LottoTickets buy(long purchaseAmount) {
        return lottoMachine.buy(new Balance(purchaseAmount));
    }

    public LottoTickets buy(List<LottoTicket> lottoTickets) {
        return lottoMachine.buy(lottoTickets);
    }

    public LottoWinningNumbers raffle(String lottos, int bonus) {
        return new LottoWinningNumbers(lottos, bonus);
    }

    public LottoResult getResult(LottoTickets tickets, LottoWinningNumbers winningNumbers) {
        return tickets.getWinningResult(winningNumbers);
    }
}
