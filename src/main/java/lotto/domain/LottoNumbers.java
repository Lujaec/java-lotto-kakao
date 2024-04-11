package lotto.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbers {

    private final Set<LottoNumber> numbers;

    public LottoNumbers(Collection<Integer> numbers) {
        validateNumbersUnique(numbers);
        this.numbers = numbers.stream()
                              .map(LottoNumber::new)
                              .collect(Collectors.toSet());
    }

    private void validateNumbersUnique(Collection<Integer> numbers) {
        Set<Integer> numberSet = Set.copyOf(numbers);
        if (numbers.size() != numberSet.size()) {
            throw new IllegalArgumentException("로또 숫자는 중복되면 안됩니다");
        }
    }

    public int countMatch(LottoNumbers lottoNumbers) {
        return lottoNumbers.countMatch(numbers);
    }

    private int countMatch(Set<LottoNumber> lottoNumbers) {
        Set<LottoNumber> unionSet = new HashSet<>(lottoNumbers);
        unionSet.retainAll(numbers);
        return unionSet.size();
    }

    public boolean containsAll(LottoNumbers lottoNumbers) {
        return this.numbers.containsAll(lottoNumbers.numbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        return numbers.stream()
                      .sorted()
                      .map(Object::toString)
                      .collect(Collectors.joining(", "));
    }
}
