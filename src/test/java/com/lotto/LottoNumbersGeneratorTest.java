package com.lotto;

import com.lotto.model.LottoNumbers;
import com.lotto.util.LottoNumbersGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumbersGeneratorTest {

    @Test
    void 각_로또_티켓은_6개의_숫자를_가지고_있어야_한다_성공() {
        LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGenerator();
        LottoNumbers lottoNumbers = lottoNumbersGenerator.generate();

        assertThat(lottoNumbers.size()).isEqualTo(6);
    }
}
