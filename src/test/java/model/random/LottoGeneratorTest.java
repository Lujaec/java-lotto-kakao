package model.random;

import model.ManualLotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import model.Amount;

import java.util.Arrays;
import java.util.List;

public class LottoGeneratorTest {
    @Test
    void 구매_금액만큼_로또가_생성된다() {
        int entireBudget = 6000;
        int manualCount = 2;
        int automaticBudget = entireBudget - manualCount * 1000;

        String manualString = "1,2,3,4,5,6 \n 1,2,3,4,5,6";
        List<String> manualList = Arrays.asList(manualString.split("\n"));

        Amount amount = Amount.amountInput(automaticBudget);

        LottoGenerator lottoGenerator = LottoGenerator.generate(manualList, amount);
        Assertions.assertThat(lottoGenerator.getEntireLottoSize()).isEqualTo(6);
    }

}
