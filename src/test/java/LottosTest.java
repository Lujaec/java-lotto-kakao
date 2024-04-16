import model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottosTest {
    @Test
    void calculateResultWithManualLottoNumbers() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

        Lottos lottos = new Lottos(List.of(lotto));

        Lotto inputLotto = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        WinningLotto winningLotto = new WinningLotto(inputLotto, bonusNumber);
        LottoResult lottoResult = lottos.calculateResult(winningLotto);

        assertEquals(LottoRank.FIRST, lottoResult.getLottoRanks().get(0));
    }

    @Test
    void calculateResultAutoLottoNumbers() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

        Lottos lottos = new Lottos(List.of(lotto));

        Lotto inputLotto = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        WinningLotto winningLotto = new WinningLotto(inputLotto, bonusNumber);
        LottoResult lottoResult = lottos.calculateResult(winningLotto);

        assertEquals(LottoRank.FIRST, lottoResult.getLottoRanks().get(0));
    }
}
