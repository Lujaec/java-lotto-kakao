package model.winningLottery;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import model.Ball;
import model.random.LottoNumbers;

import static java.util.Arrays.*;

public class WinningNumbers {
    private static final int NUMBER_COUNT = 6;

    private final Set<Ball> winningNumbers;

    public WinningNumbers(Set<Ball> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public int matchCount(LottoNumbers lottoNumbers) {
        return lottoNumbers.getLottoNumbers()
                .stream()
                .filter(Objects::nonNull)
                .mapToInt(
                        this::numberMatch
                ).sum();
    }

    private int numberMatch(Ball lottoNumber) {
        if (winningNumbers.contains(lottoNumber)) {
            return 1;
        }
        return 0;
    }

    public static WinningNumbers createBallNumbers(String ballNumberStr) {
        Set<Ball> ballNumberSet = Arrays.stream(ballNumberStr.split(","))
                .map(Integer::parseInt)
                .map(Ball::new)
                .collect(Collectors.toSet());

        validateBall(ballNumberSet);

        return new WinningNumbers(ballNumberSet);
    }

    public Set<Ball> getWinningNumbers() {
        return winningNumbers;
    }

    public static void validateBall(Set<Ball> winningNumberSet) {
        validateUniqueNumberCount(winningNumberSet);
    }

    private static void validateUniqueNumberCount(Set<Ball> winningNumberSet) {
        if (winningNumberSet.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException(String.format("서로 다른 %d개 숫자를 입력해주세요", NUMBER_COUNT));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningNumbers that = (WinningNumbers) o;
        return Objects.equals(winningNumbers, that.winningNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNumbers);
    }
}
