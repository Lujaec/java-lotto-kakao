package lotto;

public class Money {
	private final int value;

	public Money(int value) {
		this.validateAmount(value);
		this.value = value;
	}

	public int toPurchaseCount(){
		return this.value / 1000;
	}

	private void validateAmount(int value){
		if (value < 0)
			throw new IllegalArgumentException();
	}
}
