import model.Lotto;
import model.LottoNumber;
import model.LottoRank;
import model.WinningLotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WinningLottoTest {

    @Test
    public void constructorWinningLottoTest() {
        Lotto lotto = Lotto.from(
                List.of(1, 2, 3, 4, 5, 6)
        );
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        assertDoesNotThrow(() -> new WinningLotto(lotto, bonusNumber));
    }

    @Test
    public void constructorWinningLotto_thrownErrorTest() {
        Lotto lotto = Lotto.from(
                List.of(1, 2, 3, 4, 5, 6)
        );
        LottoNumber bonusNumber = LottoNumber.valueOf(6);
        assertThrows(IllegalArgumentException.class, () -> new WinningLotto(lotto, bonusNumber));
    }

    @Test
    public void makeLottoRankTest() {
        Lotto lotto = Lotto.from(
                List.of(1, 2, 3, 4, 5, 6)
        );
        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        LottoRank lottoRank = winningLotto.makeLottoRank(lotto);

        assertEquals(lottoRank, LottoRank.FIRST);
        assertNotEquals(lottoRank, LottoRank.SECOND);
        assertNotEquals(lottoRank, LottoRank.THIRD);
        assertNotEquals(lottoRank, LottoRank.FOURTH);
        assertNotEquals(lottoRank, LottoRank.FIFTH);
        assertNotEquals(lottoRank, LottoRank.FAIL);
    }
}
