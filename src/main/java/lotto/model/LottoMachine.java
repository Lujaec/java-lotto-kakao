package lotto.model;

import java.util.List;

public class LottoMachine {
    private final AutoGenerator autoGenerator;

    public LottoMachine(final AutoGenerator autoGenerator) {
        this.autoGenerator = autoGenerator;
    }

    public LottoGroup autoGenerate() {
        return new LottoGroup(autoGenerator.generate());
    }

    public LottoGroup manualGenerate(final List<Integer> numbers) {
        return new LottoGroup(numbers);
    }
}
