package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Quantity 단위 테스트")
class QuantityTest {

    @Test
    void 수량은_0이상() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(-12));
    }
}
