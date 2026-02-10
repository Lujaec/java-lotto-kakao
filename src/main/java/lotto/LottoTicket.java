package lotto;

import java.util.HashSet;
import java.util.Set;

public class LottoTicket {

	private final Set<LottoNumber> numbers;

	public LottoTicket() {
		this.numbers = new HashSet<>();
		for(int i = 1; i <= 6; ++i){
			this.numbers.add(new LottoNumber(i));
		}
	}

	public Set<LottoNumber> getNumbers() {
		return numbers;
	}
}
