package model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final Map<Integer, LottoNumber> LOTTO_NUMBERS =
        IntStream.rangeClosed(1, 45)
            .boxed()
            .collect(Collectors.toUnmodifiableMap(i -> i, LottoNumber::new));
    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호는 1부터 45까지의 숫자만 가능합니다.");
        }
        return LOTTO_NUMBERS.get(number);
    }

    public static Set<LottoNumber> of(List<Integer> numbers) {
        return numbers.stream()
            .map(LottoNumber::of)
            .collect(java.util.stream.Collectors.toSet());
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof LottoNumber && number == ((LottoNumber) obj).number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(number, o.number);
    }

    public int getNumber() {
        return number;
    }
}
