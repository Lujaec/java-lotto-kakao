import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import model.Lottos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ManualNumberGenerator;
import util.NumberGenerator;

class LottosTest {

    private Lottos lottos;
    private NumberGenerator numberGenerator;

    @BeforeEach
    public void setUp() {
        numberGenerator = new ManualNumberGenerator(Arrays.asList(
            Arrays.asList(1, 2, 3, 4, 5, 6),
            Arrays.asList(7, 8, 9, 10, 11, 12)
        ));
        lottos = new Lottos(2, numberGenerator);
    }

    @Test
    public void addManualLottoAddsLottoToLottos() {
        lottos.add(new Lottos(1, numberGenerator));
        assertEquals(3, lottos.getLottos().size());
    }

    @Test
    public void addRandomLottoAddsLottoToLottos() {
        lottos.add(new Lottos(1, numberGenerator));
        assertEquals(3, lottos.getLottos().size());
    }
}
