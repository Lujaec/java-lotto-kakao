package domain.lotto;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
	public static final int LOTTO_UPPER_BOUND = 45;
	public static final int LOTTO_LOWER_BOUND = 1;

	private final int value;

	public LottoNumber(int value) {
		validateLottoNumber(value);
		this.value = value;
	}

	private void validateLottoNumber(int lottoBall) {
		if (lottoBall < LOTTO_LOWER_BOUND || lottoBall > LOTTO_UPPER_BOUND) {
			throw new IllegalArgumentException("로또 번호는 1부터 45 까지여야 합니다.");
		}
	}

	public int getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNumber lottoNumber1 = (LottoNumber)o;
		return value == lottoNumber1.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return String.valueOf(this.value);
	}

	@Override
	public int compareTo(LottoNumber lottoNumber) {
		return this.value - lottoNumber.value;
	}
}
