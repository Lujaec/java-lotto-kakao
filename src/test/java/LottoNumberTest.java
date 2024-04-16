import model.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {

    private static final int VALID_MIN_NUMBER = 1;
    private static final int VALID_MAX_NUMBER = 45;
    private static final int INVALID_LOW_NUMBER = 0;
    private static final int INVALID_HIGH_NUMBER = 46;
    private static final int COMPARISON_BASE_NUMBER = 10;
    private static final int COMPARISON_GREATER_NUMBER = 20;

    @Test
    void createLottoNumberWithinValidRangeTest() {
        assertDoesNotThrow(() -> LottoNumber.valueOf(VALID_MIN_NUMBER), "유효한 최소 범위 숫자로 생성 시 예외가 발생하지 않아야 합니다.");
        assertDoesNotThrow(() -> LottoNumber.valueOf(VALID_MAX_NUMBER), "유효한 최대 범위 숫자로 생성 시 예외가 발생하지 않아야 합니다.");
    }

    @Test
    void createLottoNumberOutsideValidRangeThrowsExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.valueOf(INVALID_LOW_NUMBER), "유효하지 않은 낮은 범위 숫자로 생성 시 예외가 발생해야 합니다.");
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.valueOf(INVALID_HIGH_NUMBER), "유효하지 않은 높은 범위 숫자로 생성 시 예외가 발생해야 합니다.");
    }

    @Test
    void compareToOtherLottoNumberTest() {
        LottoNumber lottoNumber1 = LottoNumber.valueOf(COMPARISON_BASE_NUMBER);
        LottoNumber lottoNumber2 = LottoNumber.valueOf(COMPARISON_GREATER_NUMBER);
        assertTrue(lottoNumber1.compareTo(lottoNumber2) < 0, "lottoNumber1이 lottoNumber2보다 작아야 합니다.");
        assertTrue(lottoNumber2.compareTo(lottoNumber1) > 0, "lottoNumber2가 lottoNumber1보다 커야 합니다.");
    }

    @Test
    void equalsWithSameLottoNumberTest() {
        LottoNumber lottoNumber1 = LottoNumber.valueOf(COMPARISON_BASE_NUMBER);
        LottoNumber lottoNumber2 = LottoNumber.valueOf(COMPARISON_BASE_NUMBER);
        assertEquals(lottoNumber1, lottoNumber2, "동일한 값을 가진 두 LottoNumber는 같아야 합니다.");
    }

    @Test
    void hashCodeWithSameLottoNumberTest() {
        LottoNumber lottoNumber1 = LottoNumber.valueOf(COMPARISON_BASE_NUMBER);
        LottoNumber lottoNumber2 = LottoNumber.valueOf(COMPARISON_BASE_NUMBER);
        assertEquals(lottoNumber1.hashCode(), lottoNumber2.hashCode(), "동일한 값을 가진 두 LottoNumber의 hashCode는 같아야 합니다.");
    }

    @Test
    void lottoSameNumberCachingTest() {
        LottoNumber lottoNumber1 = LottoNumber.valueOf(COMPARISON_BASE_NUMBER);
        LottoNumber lottoNumber2 = LottoNumber.valueOf(COMPARISON_BASE_NUMBER);
        assertSame(lottoNumber1, lottoNumber2);
    }
}
