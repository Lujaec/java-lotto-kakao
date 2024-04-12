package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("LottoResult 단위 테스트")
public class LottoResultTest {

    private final LottoTicket winningLottoTicket = new LottoTicket(List.of(1, 2, 3, 4, 5, 6));
    private final LottoNumber bonusNumber = new LottoNumber(7);
    private final WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusNumber);

    @Test
    void 로또번호_당첨_개수() {
        List<LottoTicket> lottoTickets = List.of(
            new LottoTicket(List.of(11, 12, 13, 14, 15, 16)),
            new LottoTicket(List.of(1, 2, 3, 4, 7, 8))
        );

        LottoResult lottoResult = new LottoResult(lottoTickets, winningLotto);
        Map<LottoPrice, Integer> result = lottoResult.getLottoResult();

        assertThat(result).containsEntry(LottoPrice.NOTHING, 1);
        assertThat(result).containsEntry(LottoPrice.FOURTH, 1);
    }

    @Test
    void 로또번호_수익률() {
        List<LottoTicket> lottoTickets = List.of(
            new LottoTicket(List.of(11, 12, 13, 14, 15, 16)),
            new LottoTicket(List.of(1, 2, 3, 7, 8, 9))
        );

        LottoGame lottoGame = new LottoGame(winningLotto, lottoTickets);
        float result = lottoGame.calculateRevenue();

        assertThat(result).isCloseTo(2.5f, within(0.5f));
    }
}
