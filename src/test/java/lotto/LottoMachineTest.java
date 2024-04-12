package lotto;

import lotto.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {
    @Test
    void 구매_금액을_받아_로또를_발급한다() {
        LottoTicketGenerator generator = new LottoTicketGenerator();
        LottoMachine lottoMachine = new LottoMachine(generator);

        assertThat(lottoMachine.buy(new Balance(5000L)).getSize()).isEqualTo(5000 / LottoTicket.PRICE);
    }

    @Test
    void 로또를_수동으로_발급한다() {
        LottoTicketGenerator generator = new LottoTicketGenerator();
        LottoMachine lottoMachine = new LottoMachine(generator);
        List<LottoTicket> lottoTickets = List.of(
                createLottoTicket(List.of(1, 2, 3, 4, 5, 6)),
                createLottoTicket(List.of(7, 8, 9, 10, 11, 12)));

        assertThat(lottoMachine.buy(lottoTickets))
                .extracting(LottoTickets::getLottoTickets)
                .isEqualTo(lottoTickets);
    }

    private LottoTicket createLottoTicket(List<Integer> numbers) {
        return new LottoTicket(numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }
}
