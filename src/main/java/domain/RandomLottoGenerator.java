package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.Lottos;

public class RandomLottoGenerator implements LottoGenerator {
	private static final List<LottoNumber> NUMBERS;
	static {
		NUMBERS = IntStream.rangeClosed(LottoNumber.LOTTO_LOWER_BOUND, LottoNumber.LOTTO_UPPER_BOUND)
			.mapToObj(LottoNumber::new).collect(Collectors.toList());
	
	}
	
	@Override
	public Lottos generateLottos(int lottoCount) {
		return new Lottos(Stream.generate(this::generateLotto)
			.limit(lottoCount)
			.collect(Collectors.toList()));
	}

	private Lotto generateLotto() {
		Collections.shuffle(NUMBERS);
		List<LottoNumber> lottoNumbers = new ArrayList<>(NUMBERS.subList(0, Lotto.LOTTO_NUMBER_SIZE));
		return new Lotto(lottoNumbers);
	}
}
