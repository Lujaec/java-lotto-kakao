package lotto;

import java.util.ArrayList;
import java.util.function.Consumer;

public class LottoTickets {
	private final ArrayList<LottoTicket> lottoTickets;

	public LottoTickets(ArrayList<LottoTicket> lottoTickets) {
		this.lottoTickets = new ArrayList<>(lottoTickets);
	}

	public int size() {
		return lottoTickets.size();
	}

	public void forEach(Consumer<LottoTicket> action) {
		for (LottoTicket lottoTicket : lottoTickets) {
			action.accept(lottoTicket);
		}
	}

	public LottoStatistics buildStatistics(LottoAnswer lottoAnswer) {
		LottoStatistics lottoStatistics = new LottoStatistics();
		for (LottoTicket lottoTicket : lottoTickets) {
			lottoStatistics.add(lottoAnswer.judge(lottoTicket));
		}
		return lottoStatistics;
	}
}
