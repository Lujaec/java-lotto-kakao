package lotto.domain;

import java.util.List;

public class Lotto {

    public static final int LOTTO_SIZE = 6;

    private final LottoNumbers numbers;

    public Lotto(List<Integer> numbers) {
        validateNumbersLength(numbers);
        this.numbers = new LottoNumbers(numbers);
    }

    public Lotto(LottoNumbers lottoNumbers) {
        this.numbers = lottoNumbers;
    }

    private void validateNumbersLength(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 숫자는 6개여야 합니다");
        }
    }

    public int countMatch(LottoNumbers otherNumbers) {
        return numbers.countMatch(otherNumbers);
    }

    public boolean containsAll(LottoNumbers otherNumbers) {
        return numbers.containsAll(otherNumbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
