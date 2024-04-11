package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
	public static final int LOTTO_NUMBER_COUNT = 6;
	public static final int PRICE = 1000;

	private final List<LottoNumber> lottoNumbers;

	public LottoTicket(List<LottoNumber> lottoNumbers) {
		validateSize(lottoNumbers);
		validateDuplicate(lottoNumbers);
		this.lottoNumbers = new ArrayList<>(lottoNumbers);
	}

	public static LottoTicket of(List<Integer> numbers) {
		List<LottoNumber> lottoNumbers = numbers.stream()
			.map(LottoNumber::of)
			.collect(Collectors.toList());
		return new LottoTicket(lottoNumbers);
	}

	private void validateSize(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
			throw new IllegalArgumentException(
				String.format("로또 번호는 %d개여야 합니다. value: %d", LOTTO_NUMBER_COUNT, lottoNumbers.size()));
		}
	}

	private void validateDuplicate(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != lottoNumbers.stream().distinct().count()) {
			throw new IllegalArgumentException("중복된 숫자가 존재합니다.");
		}
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lottoNumbers.contains(lottoNumber);
	}

	public int countSameNumbers(LottoTicket lottoTicket) {
		return Long.valueOf(lottoNumbers.stream().filter(lottoTicket::contains).count()).intValue();
	}

	public List<LottoNumber> getLottoNumbers() {
		return new ArrayList<>(lottoNumbers);
	}
}