package lotto;

import java.util.HashSet;
import java.util.Set;

public class LottoAnswer {

	private final Set<LottoNumber> winningNumbers;
	private final LottoNumber bonus;

	public LottoAnswer(Set<LottoNumber> winningNumbers, LottoNumber bonus) {
		validateWinningNumbers(winningNumbers);
		validateBonusNumber(winningNumbers, bonus);
		this.winningNumbers = new HashSet<>(winningNumbers);
		this.bonus = bonus;
	}

	public Rank judge(LottoTicket lottoTicket) {
		int matchCount = countMatch(lottoTicket);
		boolean bonusMatch = containsBonus(lottoTicket);

		if (matchCount == 6) return Rank.FIRST;
		if (matchCount == 5 && bonusMatch) return Rank.SECOND;
		if (matchCount == 5) return Rank.THIRD;
		if (matchCount == 4) return Rank.FOURTH;
		if (matchCount == 3) return Rank.FIFTH;

		return Rank.OTHER;
	}

	private int countMatch(LottoTicket lottoTicket) {
		return (int) lottoTicket.getNumbers().stream()
			.filter(winningNumbers::contains)
			.count();
	}

	private boolean containsBonus(LottoTicket lottoTicket) {
		return lottoTicket.getNumbers().contains(bonus);
	}

	private void validateWinningNumbers(Set<LottoNumber> winningNumbers) {
		if (winningNumbers.size() != 6)
			throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
	}

	private void validateBonusNumber(Set<LottoNumber> winningNumbers, LottoNumber bonus) {
		if (winningNumbers.contains(bonus))
			throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
	}
}
