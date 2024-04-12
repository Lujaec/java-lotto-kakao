package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    void 보너스번호_로또번호_중복불가() {
        LottoTicket winningLottoTicket = new LottoTicket(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(6);

        assertThrows(IllegalArgumentException.class,
            () -> new WinningLotto(winningLottoTicket, bonusNumber));
    }

    @Test
    void 일치하는_로또번호_계산() {
        LottoTicket winningLottoTicket = new LottoTicket(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusNumber);

        LottoPrice lottoPrice = winningLotto.calculatePrize(
            new LottoTicket(List.of(1, 2, 3, 11, 12, 13)));
        assertThat(lottoPrice).isEqualTo(LottoPrice.FIFTH);
    }

    @Test
    void 일치하는_로또번호_계산_2() {
        LottoTicket winningLottoTicket = new LottoTicket(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusNumber);

        LottoPrice lottoPrice = winningLotto.calculatePrize(
            new LottoTicket(List.of(1, 2, 3, 4, 7, 8)));
        assertThat(lottoPrice).isEqualTo(LottoPrice.FOURTH);
    }

    @Test
    void 불일치하는_로또번호_계산() {
        LottoTicket winningLottoTicket = new LottoTicket(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusNumber);

        LottoPrice lottoPrice = winningLotto.calculatePrize(
            new LottoTicket(List.of(1, 2, 11, 12, 13, 14)));
        assertThat(lottoPrice).isEqualTo(LottoPrice.NOTHING);
    }
}
