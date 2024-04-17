package com.lotto.model;

import com.lotto.util.LottoGenerateStrategy;

public class LottoGame {
	public static final int LOTTO_PRICE = 1000;
	private final LottoTickets lottoTickets;
	private LottoResults lottoResults = null;

	public LottoGame(int money, LottoTickets lottoTickets) {
		this.lottoTickets = lottoTickets;
	}

	public void addManualLottoTicket(LottoTickets lottoTickets) {
		this.lottoTickets.addManualLotto(lottoTickets);
	}

	public LottoTickets getLottoTickets() {
		return lottoTickets;
	}

	public double calculateProfit() {
		if (lottoResults == null) {
			throw new RuntimeException("로또 결과가 없습니다.");
		}
		double totalPrize = lottoResults.calculateTotalPrize();
		return totalPrize / (lottoTickets.size() * LOTTO_PRICE);
	}

	public LottoResults play(TargetLotto targetLotto) {
		lottoResults = lottoTickets.playLotto(targetLotto);
		return lottoResults;
	}
}
