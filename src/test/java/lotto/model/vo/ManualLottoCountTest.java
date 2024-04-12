package lotto.model.vo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class ManualLottoCountTest {

    @ParameterizedTest(name = "수동 구매 개수가 {0}일 경우")
    @ValueSource(ints = {-10, -1})
    void 수동_구매_개수가_음수일_경우(final int manualCount) {
        // when & then
        assertThatThrownBy(() -> new ManualLottoCount(manualCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 개수는 0개 이상이어야 합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"0:true", "1:false", "2:false"}, delimiter = ':')
    void 수동_구매_개수_0_테스트(final int manualCount, final boolean expected) {
        // when & then
        assertThat(new ManualLottoCount(manualCount).isZero()).isEqualTo(expected);
    }
}
