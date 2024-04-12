package domain;

public class LottoMoney {
    private static final int LOTTO_PRICE_UNIT = 1000;

    private final int lottoMoney;

    public LottoMoney(int money) {
        if (money < LOTTO_PRICE_UNIT) {
            throw new IllegalArgumentException("돈은 1000원 이상이어야 합니다.");
        }
        this.lottoMoney = money;
    }

    public int calculateLottoCount() {
        return lottoMoney / LOTTO_PRICE_UNIT;
    }

    public int getSpentMoney() {
        return LOTTO_PRICE_UNIT * calculateLottoCount();
    }
}
