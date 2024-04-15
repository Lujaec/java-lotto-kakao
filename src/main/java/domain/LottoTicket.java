package domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {

    private static final int TICKET_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> numbers) {
        validateNotDuplicated(numbers);
        validateSize(numbers);

        this.lottoNumbers = numbers;
    }

    public LottoTicket(String LottoNumbers){
       this(Arrays.stream(LottoNumbers.split(", "))
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != TICKET_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개이어야 합니다.");
        }
    }

    private void validateNotDuplicated(List<LottoNumber> numbers) {
        Set<LottoNumber> distinctNumbers = new HashSet<>(numbers);

        if (numbers.size() != distinctNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public long matchCount(LottoTicket other) {
        return lottoNumbers.stream()
                .filter(other.lottoNumbers::contains)
                .count();
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toList());
    }
}
