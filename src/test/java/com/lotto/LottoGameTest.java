package com.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lotto.model.LottoGame;
import com.lotto.model.LottoRank;
import com.lotto.model.LottoTickets;
import com.lotto.model.TargetLotto;
import com.lotto.util.LottoGenerateStrategy;

public class LottoGameTest {
	private LottoGenerateStrategy lottoGenerateStrategy;

	@BeforeEach
	void setUp() {
		lottoGenerateStrategy = new LottoGenerateStrategy() {
			@Override
			public List<Integer> generate() {
				return List.of(1, 2, 3, 4, 5, 6);
			}
		};
	}

	@Test
	void 구입_금액에_해당하는_로또를_구매할_수_있다() {
		LottoTickets lottoTickets = new LottoTickets(14, lottoGenerateStrategy);
		LottoGame lottoGame = new LottoGame(14000, lottoTickets);
		assertThat(lottoGame.getLottoTickets().size()).isEqualTo(14);
	}

	@Test
	void 로또_게임의_수익률을_계산할_수_있다() {
		LottoTickets lottoTickets = new LottoTickets(1, lottoGenerateStrategy);
		LottoGame lottoGame = new LottoGame(1000, lottoTickets);

		lottoGame.play(new TargetLotto(List.of(1, 2, 3, 4, 5, 6), 7));
		assertThat(lottoGame.calculateProfit()).isEqualTo((int)(LottoRank.FIRST.getPrize() / 1000));
	}
}
