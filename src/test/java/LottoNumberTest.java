import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import model.LottoNumber;
import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @Test
    public void invalidNumberBelowRangeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(0));
    }

    @Test
    public void invalidNumberAboveRangeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(46));
    }

    @Test
    public void validNumberReturnsLottoNumber() {
        assertNotNull(LottoNumber.of(1));
        assertNotNull(LottoNumber.of(45));
    }

    @Test
    public void sameNumberReturnsSameLottoNumber() {
        assertEquals(LottoNumber.of(1), LottoNumber.of(1));
    }

    @Test
    public void differentNumbersReturnDifferentLottoNumbers() {
        assertNotEquals(LottoNumber.of(1), LottoNumber.of(2));
    }

    @Test
    public void ofListReturnsSetOfLottoNumbers() {
        Set<LottoNumber> expected = new HashSet<>(
            Arrays.asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3)));
        assertEquals(expected, LottoNumber.of(Arrays.asList(1, 2, 3)));
    }
}
