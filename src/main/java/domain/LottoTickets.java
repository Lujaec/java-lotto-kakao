package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

public class LottoTickets {
	private final List<LottoTicket> lottoTickets;

	public LottoTickets(List<LottoTicket> lottoTickets) {
		this.lottoTickets = new ArrayList<>(lottoTickets);
	}

	public LottoTickets plus(LottoTickets lottoTicketsToAdd) {
		List<LottoTicket> addedLottoTickets = new ArrayList<>(this.lottoTickets);

		addedLottoTickets.addAll(lottoTicketsToAdd.getLottoTickets());

		return new LottoTickets(addedLottoTickets);
	}

	public List<LottoTicket> getLottoTickets() {
		return Collections.unmodifiableList(lottoTickets);
	}

	public LottoGameResult calculateLottoGameResult(LottoTicket winningLottoNumbers, LottoNumber bonusNumber) {
		List<LottoWinningRank> lottoWinningRanks = new ArrayList<>();

		for (LottoTicket lottoTicket : lottoTickets) {
			int matchCount = lottoTicket.countSameNumbers(winningLottoNumbers);
			boolean hasBonusNumber = lottoTicket.contains(bonusNumber);

			lottoWinningRanks.add(LottoWinningRank.calculateWinningRank(matchCount, hasBonusNumber));
		}
		return LottoGameResult.of(lottoWinningRanks);
	}
}
