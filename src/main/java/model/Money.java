package model;

import static constant.LottoConstants.*;
import static util.Validator.*;

import java.util.Objects;

public class Money {
	private int money;

	public Money(String strMoney) {
		validateIsNumber(strMoney);
		int intMoney = Integer.parseInt(strMoney);
		validateCanNotBuyLotto(intMoney);
		this.money = intMoney;
	}

	public Money(int money) {
		this.money = money;
	}

	public void buyLotto(int numOfLotto) {
		validateBuyMoney(this, numOfLotto);
		this.money -= numOfLotto * LOTTO_PRICE;
	}

	public int numOfLottoCanBuy() {
		return money / LOTTO_PRICE;
	}

	public double calculateReturnRatio(int numOfLottoBought) {
		return (double)this.money / (numOfLottoBought * LOTTO_PRICE);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;
		Money money = (Money)object;
		return this.money == money.money;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}
}
