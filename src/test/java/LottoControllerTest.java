import controller.LottoController;
import model.UserLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoControllerTest {
    LottoController lottoController;
    List<UserLotto> userLottos;

    @Test
    void 사용자_수동_로또_번호_생성_테스트() {
        userLottos = Stream.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6))
                .map(UserLotto::create)
                .collect(Collectors.toList());

        lottoController = new LottoController(userLottos);

        assertThat(lottoController.getUserLottos().size()).isEqualTo(2);
    }
}
