package com.lotto.model;

public class LottoPlayer {

    private final LottoTickets lottoTickets = new LottoTickets();
    private Money balance;

    public LottoPlayer() {
        this.balance = new Money();
    }

    private static void validateMoney(Money money) {
        if (money.isLessThan(Money.valueOf(LottoTicket.PRICE))) {
            throw new IllegalArgumentException("로또 구매 최소 금액은 " + LottoTicket.PRICE + "원 입니다.");
        }
    }

    public Money getBalance() {
        return balance;
    }

    public void inputMoney(Money money) {
        validateMoney(money);
        this.balance = balance.add(money);
    }

    public void buyLottoTicket(LottoNumbers lottoNumbers) {
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        Money lottoTicketPrice = Money.valueOf(LottoTicket.PRICE);
        validateBalance(lottoTicketPrice);

        this.lottoTickets.add(lottoTicket);
        balance = balance.subtract(lottoTicketPrice);
    }

    public LottoTickets getLottoTickets() {
        return lottoTickets;
    }

    private void validateBalance(Money lottoTicketsPrice) {
        if (balance.isLessThan(lottoTicketsPrice)) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
    }
}
