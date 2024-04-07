package model;

import static constant.LottoRank.*;

import constant.LottoRank;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoResult {
	private final Map<LottoRank, Integer> result;

	public LottoResult() {
		result = Arrays.stream(LottoRank.values())
			.collect(Collectors.toMap(Function.identity(), lottoRank -> 0));
	}

	public void updateResult(int correctNumbersCount, boolean bonus) {
		LottoRank lottoRank = matchRank(correctNumbersCount, bonus);
		result.compute(lottoRank, (rank, count) -> count == null ? 1 : count + 1);
	}

	public Money getLottoPrize() {
		return new Money(Arrays.stream(LottoRank.values())
			.mapToInt(lottoRank -> lottoRank.getPrize() * result.getOrDefault(lottoRank, 0))
			.sum());
	}

	public Map<LottoRank, Integer> getResult() {
		return Collections.unmodifiableMap(result);
	}
}
