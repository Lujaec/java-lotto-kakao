import model.UserLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class UserLottoTest {
    UserLotto userLotto;

    @BeforeEach
    void setUp() {
        userLotto =  UserLotto.create(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    void 사용자_로또_길이_테스트() {
        int expected = 6;
        int actual = userLotto.getBalls().size();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 사용자_로또_길이_익셉션_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        userLotto = UserLotto.create(Arrays.asList(1,2,3,4,5,45,43))
                );
    }

    @Test
    void 사용자_로또_범위_익셉션_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        userLotto = UserLotto.create(Arrays.asList(1,2,3,4,45,46))
                );
    }
}
