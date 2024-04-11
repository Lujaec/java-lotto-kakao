package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTicketGenerator {
	public static LottoTicket generateRandomLottoTicket() {
		List<LottoNumber> shuffledNumbers = getShuffledNumbers();
		return new LottoTicket(shuffledNumbers.subList(0, LottoTicket.LOTTO_NUMBER_COUNT));
	}

	public static LottoTickets generateRandomLottoTickets(int countOfTickets) {
		return Stream.generate(LottoTicketGenerator::generateRandomLottoTicket)
			.limit(countOfTickets)
			.collect(Collectors.collectingAndThen(Collectors.toList(), LottoTickets::new));
	}

	private static List<LottoNumber> getShuffledNumbers() {
		List<LottoNumber> shuffledNumbers = new ArrayList<>(LottoNumber.values());
		Collections.shuffle(shuffledNumbers);
		return shuffledNumbers;
	}
}
