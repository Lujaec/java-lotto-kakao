package domain;

public class LottoMoney {
	private static final int LOTTO_PRICE_UNIT = 1000;

	private final int lottoMoney;

	public LottoMoney(int money) {
		if (money < 0) {
			throw new IllegalArgumentException("돈은 0원 이상이어야 합니다!");
		}
		this.lottoMoney = money;
	}

	public LottoMoney calculateRemainAfterBuy(int count) {
		int remain = this.lottoMoney - count * LOTTO_PRICE_UNIT;
		if (remain < 0) {
			throw new IllegalStateException("금액이 부족합니다!");
		}
		return new LottoMoney(remain);
	}

	public int calculateLottoCount() {
		return lottoMoney / LOTTO_PRICE_UNIT;
	}

	public int getSpentMoney() {
		return LOTTO_PRICE_UNIT * calculateLottoCount();
	}
}
