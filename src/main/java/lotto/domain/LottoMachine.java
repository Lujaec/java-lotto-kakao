package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoMachine {

    public static final long LOTTO_PRICE = 1000;
    private final LottoNumberGenerator generator;

    public LottoMachine(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public List<Lotto> issue(long money) {
        validateMoney(money);

        return Stream.generate(this::issue)
                     .limit(money / LOTTO_PRICE)
                     .collect(Collectors.toList());
    }

    private void validateMoney(long money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또를 한장도 구매할 수 없습니다");
        }
    }

    private Lotto issue() {
        return new Lotto(generator.generate());
    }
}
