package lotto;

import java.util.Objects;

public class LottoNumber {
	private final int value;

	public LottoNumber(int value) {
		validateNumber(value);
		this.value = value;
	}

	private void validateNumber(int value){
		if (!(1 <= value && value <= 45))
			throw new IllegalArgumentException();
	}

	public int getValue(){
		return this.value;
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
