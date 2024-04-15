import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.LottoNumber;
import domain.NumberGenerator;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("LottoNumberGenerator 단위 테스트")
class LottoNumberGeneratorTest {

    @Test
    void 랜덤_로또번호_생성() {
        NumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        List<LottoNumber> result = lottoNumberGenerator.generateNumbers();

        assertAll(
                () -> assertThat(result).hasSize(6),
                () -> assertThat(new HashSet<>(result)).hasSize(6)
        );
    }

}
