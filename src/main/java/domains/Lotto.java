package domains;

import utils.LottoUtils;

import java.util.*;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> numbers) {
        validateDuplication(numbers);
        this.lottoNumbers = numbers;
    }

    public static Lotto from(List<Integer> pickedNumbers) {
        return new Lotto(LottoUtils.convertList(pickedNumbers, LottoNumber::new));
    }

    public List<Integer> values() {
        return LottoUtils.convertList(lottoNumbers, LottoNumber::value);
    }

    public List<LottoNumber> numbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    private void validateDuplication(List<LottoNumber> pickedNumbers) {
        Set<LottoNumber> set = new HashSet<>(pickedNumbers);
        if (pickedNumbers.size() != set.size()) {
            throw new RuntimeException("로또 번호는 중복될 수 없습니다.");
        }
    }
}
