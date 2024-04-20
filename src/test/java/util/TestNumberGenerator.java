package util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import model.LottoNumber;

public class TestNumberGenerator implements NumberGenerator {

    private final List<List<Integer>> candidates;
    private int currentIndex = 0;

    public TestNumberGenerator(List<List<Integer>> candidates) {
        this.candidates = candidates;
    }

    public Set<LottoNumber> generateNumbers() {
        List<Integer> numbers = candidates.get(currentIndex % candidates.size());
        currentIndex++;

        return numbers.stream()
            .map(LottoNumber::of)
            .collect(Collectors.toSet());
    }
}
