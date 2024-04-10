package domain.lotto;

import domain.Rank;
import domain.lotto.Lotto;
import domain.lotto.LottoNumber;

public class WinningLotto {
	private final Lotto winnigLotto;
	private final LottoNumber bonusNumber;

	public WinningLotto(Lotto winnigLotto, LottoNumber bonusNumber) {
		if (winnigLotto.contains(bonusNumber)) {
			throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복되면 안됩니다.");
		}
		this.winnigLotto = winnigLotto;
		this.bonusNumber = bonusNumber;
	}

	public Rank getRank(Lotto lotto) {
		int matchNumberCount = winnigLotto.countSameNumber(lotto);
		boolean hasBonusNumber = hasBonusNumber(lotto);
		return Rank.findByMatchNumberCount(matchNumberCount, hasBonusNumber);
	}

	private boolean hasBonusNumber(Lotto lotto) {
		return lotto.contains(bonusNumber);
	}
}
