package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("LottoStore 단위 테스트")
public class LottoStoreTest {

    @Test
    void 자동로또_발권() {
        LottoStore lottoStore = new LottoStore(new Money(1_000));
        lottoStore.buyAutoLottos(() -> List.of(1, 2, 3, 4, 5, 6));

        assertThat(lottoStore.getManualLottoCount()).isEqualTo(0);
        assertThat(lottoStore.getAutoLottoCount()).isEqualTo(1);
    }

    @Test
    void 수동로또_발권() {
        LottoStore lottoStore = new LottoStore(new Money(2_000));
        lottoStore.buyManualLottos(
            List.of("1, 2, 3, 4, 5, 6", "1, 2, 3, 4, 5, 6")
        );

        assertThat(lottoStore.getManualLottoCount()).isEqualTo(2);
        assertThat(lottoStore.getAutoLottoCount()).isEqualTo(0);
    }
}
