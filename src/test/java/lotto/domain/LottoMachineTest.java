package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.support.StubLottoNumberGenerator;

public class LottoMachineTest {

    @Test
    void 로또를_발급한다() {
        LottoMachine machine = new LottoMachine(new StubLottoNumberGenerator());
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));

        List<Lotto> lotto = machine.issue(1000);

        assertAll(
                () -> assertThat(lotto).hasSize(1),
                () -> assertThat(lotto.get(0)).isNotNull(),
                () -> assertThat(lotto.get(0).containsAll(lottoNumbers)).isTrue()
        );
    }

    @Test
    void 로또_한장은_천원으로_여러_장을_구매할_수_있다() {
        LottoMachine machine = new LottoMachine(new StubLottoNumberGenerator());

        List<Lotto> lotto = machine.issue(5000);

        assertThat(lotto).hasSize(5);
    }

    @Test
    void 잔금은_무시한다() {
        LottoMachine machine = new LottoMachine(new StubLottoNumberGenerator());

        List<Lotto> lotto = machine.issue(5500);

        assertThat(lotto).hasSize(5);
    }

    @Test
    void 로또를_한장도_구매할_수_없다면_예외를_던진다() {
        LottoMachine machine = new LottoMachine(new StubLottoNumberGenerator());

        assertThatThrownBy(() -> machine.issue(500))
                .isInstanceOf(RuntimeException.class);
    }
}
