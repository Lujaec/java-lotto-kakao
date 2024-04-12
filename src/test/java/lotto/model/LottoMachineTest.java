package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("NonAsciiCharacters")
class LottoMachineTest {

    @Test
    void 수동_구매가_가능하다() {
        // given
        LottoMachine lottoMachine = new LottoMachine(new RandomAutoGenerator());
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // when & then
        assertThat(lottoMachine.manualGenerate(numbers).getLottoBallNumbers()).isEqualTo(numbers);
    }
}
