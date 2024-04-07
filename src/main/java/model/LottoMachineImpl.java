package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static constant.LottoConstants.*;

public class LottoMachineImpl implements LottoMachine {

	public LottoMachineImpl() {
	}

	public Lotto drawLotto() {
		Collections.shuffle(lottoNumberPool);
		List<LottoNumber> drewLottoNumbers = new ArrayList<>(lottoNumberPool.subList(0, MAX_LOTTO_NUMBER_SIZE));
		Collections.sort(drewLottoNumbers);
		return new Lotto(drewLottoNumbers);
	}

	public List<Lotto> drawMaximumLottoByMoney(Money money) {
		return IntStream.range(0, money.numOfLottoCanBuy())
			.mapToObj(i -> drawLotto())
			.collect(Collectors.toList());
	}
}
