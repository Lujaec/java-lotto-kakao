package lotto.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    public static final long PRICE = 1_000;
    public static final int SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers.stream()
                .sorted(Comparator.comparing(LottoNumber::getNumber))
                .collect(Collectors.toList());
    }

    public LottoTicket(String lottoNumbers) {
        this(Arrays.stream(lottoNumbers.split(","))
                .map(it -> new LottoNumber(Integer.parseInt(it.trim())))
                .collect(Collectors.toList()));
    }

    private void validate(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplication(lottoNumbers);
    }

    private void validateDuplication(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개만 가능합니다.");
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return this.lottoNumbers.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    public LottoRank match(LottoWinningNumbers winningNumbers) {
        int matchCount = (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
        boolean bonusMatch = lottoNumbers.stream().anyMatch(winningNumbers::matchBonus);
        return LottoRank.valueOf(matchCount, bonusMatch);
    }
}
