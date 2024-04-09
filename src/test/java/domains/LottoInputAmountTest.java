package domains;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoInputAmountTest {
    @ParameterizedTest
    @ValueSource(ints = {999, -1, 0})
    void 최소_금액보다_작은_경우_예외를_발생시킨다(int money) {
        assertThatThrownBy(() -> new LottoInputAmount(money, 0))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("로또 구매 금액은 1000 이상이어야합니다.");
    }

    @ParameterizedTest
    @CsvSource({"1000,1", "14500,14"})
    void 입력_금액과_맞는_개수의_로또를_발행한다(int money, int numberOfLottos) {
        LottoInputAmount lottoInputAmount = new LottoInputAmount(money, 0);
        assertThat(lottoInputAmount.getNumberOfLottos()).isEqualTo(numberOfLottos);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void 수동_구매_개수가_유효하지_않은_경우_예외를_발생시킨다(int numberOfManualLotto) {
        int money = 10000;
        assertThatThrownBy(() -> new LottoInputAmount(money, numberOfManualLotto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("수동 구매 개수는 0 이상이어야합니다.");
    }

    @ParameterizedTest
    @CsvSource({"1000, 2", "9000, 12"})
    void 입력_금액보다_수동_구매_개수가_크면_예외를_발생시킨다(int money, int numberOfManualLotto) {
        assertThatThrownBy(() -> new LottoInputAmount(money, numberOfManualLotto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("입력된 금액으로 수동 로또를 모두 구매할 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"5000, 2, 3", "18000, 12, 6"})
    void 입력_금액과_수동_구매_개수에_따라_유효한_자동_로또_개수를_계산한다(int money, int numberOfManualLotto, int expectedNumberOfAutoLotto) {
        LottoInputAmount lottoInputAmount = new LottoInputAmount(money, numberOfManualLotto);

        assertThat(lottoInputAmount.getNumberOfAutoLottos())
                .isEqualTo(expectedNumberOfAutoLotto);
    }
}
