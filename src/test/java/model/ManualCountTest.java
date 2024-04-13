package model;

import model.random.LottoGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ManualCountTest {
    @Test
    void 수동구매수는_1_이상이_아니면_exception_출력() {
        int budget = 10000;
        int manualAmountInput = -1;
        ManualLotto manualLotto = new ManualLotto();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    manualLotto.manualCountInput(budget, manualAmountInput);
                }).withMessage("구입할 수동 로또 수를 0개 이상 입력하세요.");
    }

    @Test
    void 수동구매수는_전체구매수보다_적게_입력받는다() {
        int budget = 10000;
        int manualAmountInput = 11;
        ManualLotto manualLotto = new ManualLotto();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    manualLotto.manualCountInput(budget, manualAmountInput);
                }).withMessage("전체 구입 수보다 적은 수를 입력하세요.");
    }

    @Test
    void 수동구매_로또만_구매한다() {
        int entireBudget = 1000;
        int manualCount = 1;
        int automaticBudget = entireBudget - manualCount * 1000;
        List<String> manualList = Arrays.asList("1,2,3,4,5,6");

        Amount amount = Amount.amountInput(automaticBudget);
        LottoGenerator lottoGenerator = LottoGenerator.generate(manualList, amount);

        Assertions.assertThat(lottoGenerator.getEntireLottoSize()).isEqualTo(1);
    }

    @Test
    void 수동구매_로또는_미구매가_가능하다() {
        int entireBudget = 1000;
        int manualCount = 0;
        int automaticBudget = entireBudget - manualCount * 1000;
        List<String> manualList = Arrays.asList();

        Amount amount = Amount.amountInput(automaticBudget);
        LottoGenerator lottoGenerator = LottoGenerator.generate(manualList, amount);

        Assertions.assertThat(lottoGenerator.getEntireLottoSize()).isEqualTo(1);
    }
}
