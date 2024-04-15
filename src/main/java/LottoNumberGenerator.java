import domain.LottoNumber;
import domain.NumberGenerator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberGenerator implements NumberGenerator {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;
    private static final int NUMBER_SIZE = 6;
    private List<LottoNumber> NUMBERS_LIST = IntStream.rangeClosed(MIN_VALUE, MAX_VALUE)
            .boxed()
            .map(LottoNumber::new)
            .collect(Collectors.toList());

    @Override
    public List<LottoNumber> generateNumbers() {

        Collections.shuffle(NUMBERS_LIST);

        return NUMBERS_LIST.subList(0, NUMBER_SIZE).stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
