package com.lotto.model;

import com.lotto.util.LottoNumbersGenerator;

import java.util.List;

public class LottoGame {

    private final LottoPlayer lottoPlayer;
    private final AutoLottoMachine autoLottoMachine;


    public LottoGame() {
        this.lottoPlayer = new LottoPlayer();
        this.autoLottoMachine = new AutoLottoMachine(new LottoNumbersGenerator());
    }

    public LottoResults play(TargetLotto targetLotto) {
        LottoResults lottoResults = new LottoResults();
        LottoTickets lottoTickets = lottoPlayer.getLottoTickets();
        List<LottoRank> lottoRanks = lottoTickets.matchAll(targetLotto);
        lottoResults.applyLottoRanks(lottoRanks);
        return lottoResults;
    }

    public int buyLottos(Money money, List<List<Integer>> manualLottoTicketNumbers) {
        lottoPlayer.inputMoney(money);
        buyManualTickets(manualLottoTicketNumbers);
        return buyAutoTickets();
    }

    private int buyAutoTickets() {
        Money balance = lottoPlayer.getBalance();
        int autoLottoTicketSize = balance.divide(Money.valueOf(LottoTicket.PRICE)).intValue();
        for (int i = 0; i < autoLottoTicketSize; i++) {
            LottoNumbers lottoNumbers = autoLottoMachine.generateLottoNumbers();
            lottoPlayer.buyLottoTicket(lottoNumbers);
        }
        return autoLottoTicketSize;
    }

    private void buyManualTickets(List<List<Integer>> manualLottoTicketNumbers) {
        manualLottoTicketNumbers.forEach(numbers -> {
            LottoNumbers lottoNumbers = LottoNumbers.valueOf(numbers);
            lottoPlayer.buyLottoTicket(lottoNumbers);
        });
    }
}
