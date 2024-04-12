package lotto;

import lotto.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {

    @Test
    void 로또_티켓을_담는다() {

        List<LottoTicket> lottoTicketList = List.of(
                new LottoTicket(List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )),
                new LottoTicket(List.of(
                        new LottoNumber(7),
                        new LottoNumber(8),
                        new LottoNumber(9),
                        new LottoNumber(10),
                        new LottoNumber(11),
                        new LottoNumber(12)
                ))
        );
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);

        assertThat(lottoTickets.getSize()).isEqualTo(lottoTicketList.size());
    }

    @Test
    void 로또_당첨_결과를_반환한다() {
        LottoWinningNumbers lottoWinningNumbers = new LottoWinningNumbers(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ), new LottoNumber(7));

        LottoTickets lottoTickets = new LottoTickets(List.of(
                new LottoTicket(List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )),
                new LottoTicket(List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(7)
                ))
        ));

        assertThat(lottoTickets.getWinningResult(lottoWinningNumbers))
                .isEqualTo(new LottoResult(List.of(LottoRank.FIRST, LottoRank.SECOND), new Balance(2 * LottoTicket.PRICE)));
    }

    @Test
    void 로또_티켓들을_추가한다() {
        LottoTickets basicTickets = new LottoTickets(List.of(createLottoTicket(List.of(1, 2, 3, 4, 5, 6))));
        LottoTickets additionalTickets = new LottoTickets(List.of(createLottoTicket(List.of(7, 8, 9, 10, 11, 12))));

        basicTickets.addAll(additionalTickets);

        assertThat(basicTickets.getLottoTickets())
                .extracting(LottoTicket::getLottoNumbers)
                .containsAll(List.of(
                        createLottoTicket(List.of(1, 2, 3, 4, 5, 6)).getLottoNumbers(),
                        createLottoTicket(List.of(7, 8, 9, 10, 11, 12)).getLottoNumbers())
                );
    }

    private LottoTicket createLottoTicket(List<Integer> lottoNumbers) {
        return new LottoTicket(lottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }
}
