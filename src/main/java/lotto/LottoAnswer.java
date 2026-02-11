package lotto;

public class LottoAnswer {
	private final LottoTicket winningTicket;
	private final LottoNumber bonusNumber;

	public LottoAnswer(LottoTicket winningTicket, LottoNumber bonusNumber) {
		validateBonusNumber(winningTicket, bonusNumber);
		this.winningTicket = winningTicket;
		this.bonusNumber = bonusNumber;
	}

	public Rank judge(LottoTicket lottoTicket) {
		int matchCount = lottoTicket.getLottoNumbers().matchCount(winningTicket.getLottoNumbers());
		boolean bonusMatched = containsBonus(lottoTicket);
		return Rank.from(matchCount, bonusMatched);
	}

	private boolean containsBonus(LottoTicket lottoTicket) {
		return lottoTicket.getLottoNumbers().contains(bonusNumber);
	}

	private void validateBonusNumber(LottoTicket winningTicket, LottoNumber bonusNumber) {
		if (winningTicket.getLottoNumbers().contains(bonusNumber)) {
			throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
		}
	}
}
