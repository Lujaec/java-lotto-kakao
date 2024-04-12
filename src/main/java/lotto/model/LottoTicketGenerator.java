package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoTicketGenerator {

    private static final List<LottoNumber> LOTTO_NUMBERS;

    static {
        LOTTO_NUMBERS = IntStream.rangeClosed(LottoNumber.LOWER_BOUND, LottoNumber.UPPER_BOUND)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public static LottoTickets generate(LottoPurchaseAmount lottoPurchaseAmount) {
        int count = (int) lottoPurchaseAmount.getPurchaseAmount() / LottoTicket.PRICE;
        return new LottoTickets(generate(count));
    }

    private static List<LottoTicket> generate(int count) {
        return Stream.generate(LottoTicketGenerator::generate)
                .limit(count)
                .collect(Collectors.toList());
    }

    private static LottoTicket generate() {
        List<LottoNumber> copiedLottoNumbers = new ArrayList<>(LOTTO_NUMBERS);
        Collections.shuffle(copiedLottoNumbers);
        return copiedLottoNumbers.stream()
                .limit(LottoTicket.SIZE)
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoTicket::new));
    }
}
