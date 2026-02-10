package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

	@Test
	@DisplayName("LottoTickets size는 보유 티켓 개수를 반환한다")
	void size() {
		LottoTickets lottoTickets = new LottoTickets(tickets(List.of(
			numbers(1, 2, 3, 4, 5, 6),
			numbers(2, 3, 4, 5, 6, 7)
		)));
		assertThat(lottoTickets.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("forEach는 모든 티켓을 순회한다")
	void for_each() {
		LottoTickets lottoTickets = new LottoTickets(tickets(List.of(
			numbers(1, 2, 3, 4, 5, 6),
			numbers(2, 3, 4, 5, 6, 7),
			numbers(3, 4, 5, 6, 7, 8)
		)));
		List<LottoTicket> visited = new ArrayList<>();

		lottoTickets.forEach(visited::add);

		assertThat(visited).hasSize(3);
	}

	@Test
	@DisplayName("buildStatistics는 티켓별 판정 결과를 집계한다")
	void build_statistics() {
		LottoTickets lottoTickets = new LottoTickets(tickets(List.of(
			numbers(1, 2, 3, 4, 5, 6),
			numbers(1, 2, 3, 4, 5, 7),
			numbers(1, 2, 3, 4, 5, 8),
			numbers(1, 2, 3, 4, 8, 9),
			numbers(1, 2, 3, 8, 9, 10),
			numbers(8, 9, 10, 11, 12, 13)
		)));
		LottoAnswer lottoAnswer = new LottoAnswer(
			new LottoTicket(numbers(1, 2, 3, 4, 5, 6)),
			new LottoNumber(7)
		);

		LottoStatistics lottoStatistics = lottoTickets.buildStatistics(lottoAnswer);

		assertThat(lottoStatistics.countOf(Rank.FIRST)).isEqualTo(1);
		assertThat(lottoStatistics.countOf(Rank.SECOND)).isEqualTo(1);
		assertThat(lottoStatistics.countOf(Rank.THIRD)).isEqualTo(1);
		assertThat(lottoStatistics.countOf(Rank.FOURTH)).isEqualTo(1);
		assertThat(lottoStatistics.countOf(Rank.FIFTH)).isEqualTo(1);
		assertThat(lottoStatistics.countOf(Rank.OTHER)).isEqualTo(1);
	}

	private ArrayList<LottoTicket> tickets(List<ArrayList<LottoNumber>> ticketNumbers) {
		ArrayList<LottoTicket> lottoTickets = new ArrayList<>();
		for (ArrayList<LottoNumber> numbers : ticketNumbers) {
			lottoTickets.add(new LottoTicket(numbers));
		}
		return lottoTickets;
	}

	private ArrayList<LottoNumber> numbers(int... values) {
		ArrayList<LottoNumber> lottoNumbers = new ArrayList<>();
		for (int value : Arrays.stream(values).boxed().toList()) {
			lottoNumbers.add(new LottoNumber(value));
		}
		return lottoNumbers;
	}
}
