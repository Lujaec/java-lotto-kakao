package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoTicket {

	private final Set<LottoNumber> numbers;

	public LottoTicket() {
		this.numbers = new HashSet<>();
		for(Integer number: generateUniqueSixNumbers()){
			this.numbers.add(new LottoNumber(number));
		}
	}

	public Set<LottoNumber> getNumbers() {
		return numbers;
	}

	private Set<Integer> generateUniqueSixNumbers() {
		List<Integer> numbers = new ArrayList<>();
		for (int i = 1; i <= 45; i++) {
			numbers.add(i);
		}

		Collections.shuffle(numbers);

		return new HashSet<>(numbers.subList(0, 6));
	}
}
