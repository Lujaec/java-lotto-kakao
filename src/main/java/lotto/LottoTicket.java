package lotto;

import java.util.ArrayList;

public class LottoTicket {
	private final LottoNumbers lottoNumbers;

	public LottoTicket() {
		this(LottoNumbers.random());
	}

	public LottoTicket(ArrayList<LottoNumber> numbers) {
		this(new LottoNumbers(numbers));
	}

	public LottoTicket(LottoNumbers lottoNumbers) {
		this.lottoNumbers = lottoNumbers;
	}

	public LottoNumbers getLottoNumbers() {
		return lottoNumbers;
	}

	public ArrayList<LottoNumber> getNumbers() {
		return lottoNumbers.values();
	}
}
