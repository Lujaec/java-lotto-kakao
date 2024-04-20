package util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import model.LottoNumber;

public class ManualNumberGenerator implements NumberGenerator {

    private final List<Set<Integer>> numbers;
    private int count = 0;

    public ManualNumberGenerator(List<List<Integer>> numbers) {
        this.numbers = numbers.stream()
            .map(HashSet::new)
            .collect(Collectors.toList());
    }

    @Override
    public Set<LottoNumber> generateNumbers() {
        // lottos 에서 각 Set<Integer> 를 LottoNumber 로 변환하여 Set<LottoNumber> 로 만들어 반환한다.
        return numbers.get(count++ % numbers.size()).stream()
            .map(LottoNumber::of)
            .collect(Collectors.toSet());
    }
}
