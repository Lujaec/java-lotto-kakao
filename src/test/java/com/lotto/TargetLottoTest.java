package com.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.lotto.model.LottoNumber;
import com.lotto.model.LottoNumbers;
import com.lotto.model.LottoRank;
import com.lotto.model.LottoTicket;
import com.lotto.model.TargetLotto;

public class TargetLottoTest {
	@Test
	void 지난주_로또_객체를_생성할_수_있다() {
		TargetLotto targetLotto = new TargetLotto(List.of(1, 2, 3, 4, 5, 6), 7);

		LottoNumbers numbers = targetLotto.getNumbers();
		LottoNumber bonusNumber = targetLotto.getBonusNumber();

		assertThat(numbers.size()).isEqualTo(6);
		assertThat(bonusNumber.getNumber()).isEqualTo(7);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,2-3", "1,2,3-6", "1,2:3-6"}, delimiter = '-')
	void 각_로또별로_숫자가_일치하는_갯수를_구할_수_있다() {
		TargetLotto targetLotto = new TargetLotto(List.of(1, 2, 3, 4, 5, 6), 7);

		LottoTicket lottoTicket = new LottoTicket(List.of(1, 2, 3, 4, 5, 6));

		LottoRank lottoRank = targetLotto.match(lottoTicket);

		assertThat(lottoRank.getPrize()).isEqualTo(LottoRank.FIRST.getPrize());
	}

	@ParameterizedTest
	@CsvSource(value = {"1,2,3,4,5,6-2000000000",
		"1,2,3,4,5,7-30000000",
		"1,2,3,4,5,8-1500000",
		"1,2,3,4,8,9-50000",
		"1,2,3,8,9,10-5000",
		"1,2,8,9,10,11-0",
		"1,8,9,10,11,12-0",
		"8,9,10,11,12,13-0"}, delimiter = '-')
	void 각_로또별로_숫자가_일치하는_갯수를_구할_수_있다_보너스_번호_포함(String input, String expect) {
		List<Integer> numbers = Stream.of(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());
		TargetLotto targetLotto = new TargetLotto(List.of(1, 2, 3, 4, 5, 6), 7);

		LottoTicket lottoTicket = new LottoTicket(numbers);

		LottoRank lottoRank = targetLotto.match(lottoTicket);

		assertThat(lottoRank.getPrize()).isEqualTo(Integer.parseInt(expect));
	}
}
