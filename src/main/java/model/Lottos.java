package model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public List<List<Integer>> getLottoNumbersList() {
		return lottos.stream()
			.map(Lotto::getLottoNumbers)
			.collect(Collectors.toList());
	}

	public List<Lotto> getLottos() {
		return Collections.unmodifiableList(lottos);
	}

	public int getLottosSize() {
		return this.lottos.size();
	}
}
