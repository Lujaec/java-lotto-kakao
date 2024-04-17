package com.lotto.model;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoNumber {
	private final int number;

	public LottoNumber(int number) {
		if (number < 1 || number > 45) {
			throw new IllegalArgumentException("로또 번호는 1부터 45까지 가능합니다.");
		}
		this.number = number;
	}

	public static LottoNumbers of(List<Integer> numbers) {
		return numbers.stream()
			.map(LottoNumber::new)
			.collect(Collectors.collectingAndThen(Collectors.toList(), LottoNumbers::new));
	}

	public int getNumber() {
		return number;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		LottoNumber that = (LottoNumber)obj;
		return number == that.number;
	}

	@Override
	public String toString() {
		return String.valueOf(number);
	}
}
