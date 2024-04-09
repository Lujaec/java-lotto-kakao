package dtos;

import domains.Lotto;

import java.util.List;

public class LottoDto {
    private final List<Integer> numbers;

    public LottoDto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static LottoDto from(Lotto lotto) {
        return new LottoDto(lotto.values());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
