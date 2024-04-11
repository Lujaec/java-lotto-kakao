import model.LottoMachine;
import model.Strategy;
import model.StrategyImpl;
import model.UserLotto;
import model.vo.LottoBall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static constant.LottoConstants.NUMBER_LOWER_BOUND;
import static constant.LottoConstants.NUMBER_UPPER_BOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoMachineTest {
    LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine();
    }

    @Test
    void 랜덤_로또_길이_테스트() {
        UserLotto userLotto = lottoMachine.extractLottoNumbers(new StrategyImpl());
        assertThat(userLotto.getBalls().size()).isEqualTo(6);
    }

    @Test
    void 랜덤_번호_범위_테스트() {
        UserLotto userLotto = lottoMachine.extractLottoNumbers(new StrategyImpl());
        for (LottoBall ball : userLotto.getBalls()) {
            assertTrue(ball.getNumber() >= NUMBER_LOWER_BOUND);
            assertTrue(ball.getNumber() <= NUMBER_UPPER_BOUND);
        }
    }

    @Test
    void 로또_번호_추출_테스트() {
        Strategy strategy = () -> UserLotto.create(List.of(1, 2, 3, 4, 5, 6));
        UserLotto expected = UserLotto.create(List.of(1, 2, 3, 4, 5, 6));
        UserLotto actual = lottoMachine.extractLottoNumbers(strategy);
        assertThat(actual).isEqualTo(expected);
    }
}
