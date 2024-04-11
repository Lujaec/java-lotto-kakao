package domain;

public class WinningLottoTicket {

	private final LottoTicket winningLottoTicket;
	private final LottoNumber bonusNumber;

	public WinningLottoTicket(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
		validateDuplicateBonusNumber(winningLottoTicket, bonusNumber);
		this.winningLottoTicket = winningLottoTicket;
		this.bonusNumber = bonusNumber;
	}

	private void validateDuplicateBonusNumber(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
		if (winningLottoTicket.contains(bonusNumber)) {
			throw new IllegalArgumentException(
				String.format("보너스 번호는 당첨 번호에 포함되선 안됩니다. LottoTicket: %s, bonusNumber: %s", winningLottoTicket,
					bonusNumber));
		}
	}

	public LottoGameResult calculateWinningRanksOfTickets(LottoTickets lottoTickets) {
		return lottoTickets.calculateLottoGameResult(winningLottoTicket, bonusNumber);
	}
}
