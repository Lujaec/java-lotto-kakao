package lotto;

import java.util.Objects;

public class LottoNumber {
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;
	private final int value;

	public LottoNumber(int value) {
		validateNumber(value);
		this.value = value;
	}

	private void validateNumber(int value) {
		if (value < MIN_NUMBER || value > MAX_NUMBER) {
			throw new IllegalArgumentException("로또 번호는 1부터 45 사이여야 합니다.");
		}
	}

	public int getValue() {
		return value;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof LottoNumber lottoNumber)) {
			return false;
		}
		return value == lottoNumber.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
