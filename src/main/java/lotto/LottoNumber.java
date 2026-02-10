package lotto;

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
}
