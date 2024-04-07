package model;

import static util.Validator.*;

import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoNumbers {
	private final LottoNumber bonus;
	private final Lotto winningNumbers;

	public WinningLottoNumbers(List<Integer> winningNumbers, LottoNumber bonus) {
		this.winningNumbers = new Lotto(winningNumbers.stream()
			.map(LottoNumber::getLottoNumberFromPool)
			.collect(Collectors.toList()));

		validateBonusNotInWinningNumbers(this.winningNumbers, bonus);
		this.bonus = bonus;
	}

	public int matchNumbers(Lotto lotto) {
		return this.winningNumbers.matchLottoNumbers(lotto);
	}

	public boolean hasBonusNumber(Lotto lotto) {
		return lotto.hasLottoNumber(bonus);
	}
}
