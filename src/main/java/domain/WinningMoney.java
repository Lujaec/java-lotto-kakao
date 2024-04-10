package domain;

import java.util.Objects;

public class WinningMoney {
	private long money;

	public WinningMoney(long money) {
		this.money = money;
	}

	public void add(WinningMoney winningMoney) {
		this.money += winningMoney.getMoney();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		WinningMoney that = (WinningMoney)o;
		return money == that.money;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}

	public long getMoney() {
		return money;
	}
}
