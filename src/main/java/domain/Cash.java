package domain;

import java.util.Objects;

public class Cash {

	private int value;

	public Cash(int value) {
		validateNegative(value);
		validateLessThanTicketPrice(value);
		this.value = value;
	}

	private void validateNegative(int value) {
		if (value < 0) {
			throw new IllegalArgumentException(String.format("Cash의 값은 음수가 아니여야 합니다. value: %d", value));
		}
	}

	private void validateLessThanTicketPrice(int value) {
		if (value < LottoTicket.PRICE) {
			throw new IllegalArgumentException(
				String.format("잔여 Cash가 부족합니다. value: %d < price: %d", value, LottoTicket.PRICE));
		}
	}

	public int getTicketCount() {
		return value / LottoTicket.PRICE;
	}

	public void purchaseLottoTickets(int countOfTickets) {
		this.value -= LottoTicket.PRICE * countOfTickets;
		validateNegative(value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Cash cash = (Cash)o;
		return value == cash.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
