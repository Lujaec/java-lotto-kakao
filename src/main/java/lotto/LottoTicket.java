package lotto;

import java.util.List;

public class LottoTicket {
	private final LottoNumbers lottoNumbers;

	public LottoTicket() {
		this(LottoNumbers.random());
	}

	public LottoTicket(List<LottoNumber> numbers) {
		this(new LottoNumbers(numbers));
	}

	public LottoTicket(LottoNumbers lottoNumbers) {
		this.lottoNumbers = lottoNumbers;
	}

	public LottoNumbers getLottoNumbers() {
		return lottoNumbers;
	}

	public List<LottoNumber> getNumbers() {
		return lottoNumbers.values();
	}
}
