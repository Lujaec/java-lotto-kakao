package com.lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lotto.util.LottoGenerateStrategy;

public class LottoTickets {
	private final List<LottoTicket> lottoTickets;

	public LottoTickets() {
		this.lottoTickets = new ArrayList<>();
	}

	public LottoTickets(int amount, LottoGenerateStrategy lottoGenerateStrategy) {
		lottoTickets = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			List<Integer> numbers = lottoGenerateStrategy.generate();
			lottoTickets.add(new LottoTicket(numbers));
		}
	}

	public List<LottoTicket> getLottoTickets() {
		return lottoTickets;
	}

	public int size() {
		return lottoTickets.size();
	}

	public void add(LottoTicket lottoTicket) {
		lottoTickets.add(lottoTicket);
	}

	public void addManualLotto(LottoTickets lottoTickets) {
		this.lottoTickets.addAll(0, lottoTickets.getLottoTickets());
	}

	public LottoResults playLotto(TargetLotto targetLotto) {
		LottoResults lottoResults = new LottoResults();
		getLottoTickets().stream()
			.map(targetLotto::match)
			.forEach(lottoResults::addLottoCount);
		return lottoResults;
	}
}
