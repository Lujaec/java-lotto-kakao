package domain.lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
	public static final int LOTTO_NUMBER_SIZE = 6;

	private final List<LottoNumber> lottoNumbers;

	public Lotto(List<LottoNumber> lottoNumbers) {
		validateSize(lottoNumbers);
		validateDistinction(lottoNumbers);

		Collections.sort(lottoNumbers);
		this.lottoNumbers = lottoNumbers;
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lottoNumbers.contains(lottoNumber);
	}

	public int countSameNumber(Lotto lotto) {
		return (int) lotto.getLottoNumbers().stream().filter(this::contains).count();
	}

	private void validateDistinction(List<LottoNumber> lottoNumbers) {
		Set<LottoNumber> ballSet = new HashSet<>(lottoNumbers);

		if (ballSet.size() != lottoNumbers.size()) {
			throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
		}
	}

	private void validateSize(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
			throw new IllegalArgumentException("로또 번호는 6개로 이뤄져야 합니다!");
		}
	}

	public List<LottoNumber> getLottoNumbers() {
		return Collections.unmodifiableList(lottoNumbers);
	}
}
