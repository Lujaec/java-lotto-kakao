package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("LottoNumber 단위 테스트")
public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 로또번호는_45이하(int input) {
        assertThatThrownBy(() -> new LottoNumber(input)).isInstanceOf(
            IllegalArgumentException.class);
    }
}
