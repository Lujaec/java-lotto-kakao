package domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {

    private static final int TICKET_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(String lottoNumbers) {
        this(Arrays.stream(lottoNumbers.split(", "))
            .map(Integer::parseInt)
            .collect(Collectors.toList()));
    }

    public LottoTicket(List<Integer> numbers) {
        validateNotDuplicated(numbers);
        validateSize(numbers);

        this.lottoNumbers = numbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != TICKET_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개이어야 합니다.");
        }
    }

    private void validateNotDuplicated(List<Integer> numbers) {
        Set<Integer> distinctNumbers = new HashSet<>(numbers);

        if (numbers.size() != distinctNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public long compare(LottoTicket other) {
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
