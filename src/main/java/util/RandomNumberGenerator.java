package util;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.LottoNumber;

public class RandomNumberGenerator implements NumberGenerator {

    private static final List<Integer> NUMBERS = IntStream.rangeClosed(1, 45)
        .boxed()
        .collect(Collectors.toList());

    public Set<LottoNumber> generateNumbers() {
        Collections.shuffle(NUMBERS);
        return NUMBERS.stream()
            .limit(6)
            .map(LottoNumber::of)
            .collect(Collectors.toSet());
    }
}
