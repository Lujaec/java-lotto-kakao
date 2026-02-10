package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoStatisticsTest {

	@Test
	@DisplayName("로또 통계 생성")
	public void lotto_statistic_add () {
		LottoStatistics lottoStatistics = new LottoStatistics();

		List<Rank> ranks = List.of(
			Rank.OTHER, Rank.OTHER, Rank.OTHER,
			Rank.OTHER, Rank.OTHER, Rank.OTHER,
			Rank.OTHER, Rank.OTHER, Rank.OTHER,
			Rank.FIFTH
		);
		final long totalPrizeMoney = ranks.stream().mapToLong(Rank::prizeMoney).sum();
		final int totalMoneySpent = ranks.size() * 1000;
		final double profitRate = (double) totalPrizeMoney / totalMoneySpent;

		// 통계생성
		for(Rank rank: ranks){
			lottoStatistics.add(rank);
		}

		assertThat(lottoStatistics.profitRate()).isEqualTo(profitRate);
		assertThat(lottoStatistics.totalPrizeMoney()).isEqualTo(totalPrizeMoney);
	}

	@Test
	@DisplayName("뽑은 로또가 없을 때 엣지케이스")
	public void divide_by_zero () {
		LottoStatistics lottoStatistics = new LottoStatistics();

		List<Rank> ranks = List.of();

		for(Rank rank: ranks){
			lottoStatistics.add(rank);
		}

		assertThat(lottoStatistics.profitRate()).isEqualTo(0);
		assertThat(lottoStatistics.totalPrizeMoney()).isEqualTo(0);
	}

	@Test
	@DisplayName("countOf는 등수별 누적 개수를 반환한다")
	public void count_of_rank() {
		LottoStatistics lottoStatistics = new LottoStatistics();
		List<Rank> ranks = List.of(
			Rank.FIFTH, Rank.FIFTH, Rank.FIFTH,
			Rank.FOURTH, Rank.FOURTH,
			Rank.OTHER
		);

		for (Rank rank : ranks) {
			lottoStatistics.add(rank);
		}

		assertThat(lottoStatistics.countOf(Rank.FIFTH)).isEqualTo(3);
		assertThat(lottoStatistics.countOf(Rank.FOURTH)).isEqualTo(2);
		assertThat(lottoStatistics.countOf(Rank.OTHER)).isEqualTo(1);
	}

	@Test
	@DisplayName("countOf는 없는 등수를 조회하면 0을 반환한다")
	public void count_of_missing_rank() {
		LottoStatistics lottoStatistics = new LottoStatistics();
		lottoStatistics.add(Rank.FIFTH);
		lottoStatistics.add(Rank.OTHER);

		assertThat(lottoStatistics.countOf(Rank.FIRST)).isEqualTo(0);
	}
}
