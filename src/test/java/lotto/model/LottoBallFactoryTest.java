package lotto.model;

import lotto.model.vo.LottoBall;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertSame;

@SuppressWarnings("NonAsciiCharacters")
class LottoBallFactoryTest {

    @ParameterizedTest(name = "번호가 {0}일 경우")
    @ValueSource(ints = {-1, 0, 46, 86, 231})
    void 번호가_1이상_45이하가_아닌_경우(final int number) {
        // when & then
        assertThatThrownBy(() -> LottoBallFactory.getLottoBall(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "번호가 {0}일 경우")
    @ValueSource(ints = {1, 2, 3, 10, 45})
    void 캐싱된_로또볼을_가져올_수_있다(final int number) {
        // given
        LottoBall lottoBall = LottoBallFactory.getLottoBall(number);

        // when
        LottoBall cachedLottoBall = LottoBallFactory.getLottoBall(number);

        // then
        assertSame(lottoBall, cachedLottoBall);
    }
}
