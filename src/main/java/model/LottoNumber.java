package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    private static final int LOTTO_NUMBER_UPPER_BOUND = 45;
    public static final List<LottoNumber> LOTTO_NUMBERS;

    static {
        LOTTO_NUMBERS = IntStream.rangeClosed(LOTTO_NUMBER_LOWER_BOUND, LOTTO_NUMBER_UPPER_BOUND)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private final int lottoNumber;

    private LottoNumber(final int number) {
        this.lottoNumber = number;
    }

    public static LottoNumber valueOf(final int number) {
        validateLottoNumber(number);
        return LOTTO_NUMBERS.get(number - 1);
    }

    private static void validateLottoNumber(int lottoNumber) {
        if (lottoNumber < LOTTO_NUMBER_LOWER_BOUND || lottoNumber > LOTTO_NUMBER_UPPER_BOUND) {
            throw new IllegalArgumentException("로또에 사용할 수 없는 숫자입니다. lottoNumber: " + lottoNumber);
        }
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.lottoNumber, o.getLottoNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.getLottoNumber();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(lottoNumber);
    }
}
