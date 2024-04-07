package model;

import static util.Validator.*;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
	private final List<LottoNumber> lottoNumbers;

	public Lotto(List<LottoNumber> lottoNumbers) {
		validateDuplicateLottoNumber(lottoNumbers);
		validateLottoNumberSize(lottoNumbers);
		this.lottoNumbers = lottoNumbers;
	}

	public List<Integer> getLottoNumbers() {
		return lottoNumbers.stream()
			.mapToInt(LottoNumber::getLottoNumber)
			.boxed()
			.collect(Collectors.toList());
	}

	public Boolean hasLottoNumber(LottoNumber lottoNumber) {
		return lottoNumbers.contains(lottoNumber);
	}

	public int matchLottoNumbers(Lotto lotto) {
		return (int)lottoNumbers.stream()
			.filter(lotto::hasLottoNumber)
			.count();
	}

}
