package lotto.support;

import java.util.List;

import lotto.domain.LottoNumberGenerator;
import lotto.domain.LottoNumbers;

public class StubLottoNumberGenerator implements LottoNumberGenerator {

    private final List<Integer> numbers;

    public StubLottoNumberGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public StubLottoNumberGenerator() {
        this(List.of(1, 2, 3, 4, 5, 6));
    }

    @Override
    public LottoNumbers generate() {
        return new LottoNumbers(numbers);
    }
}
