package model.winningLottery;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import model.Ball;
import model.LottoNumbers;

public class WinningNumbers {
    private final Set<Ball> winningNumbers;

    private WinningNumbers(Set<Ball> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public int matchCount(LottoNumbers lottoNumbers) {
        return lottoNumbers.getLottoNumbers()
            .stream()
            .mapToInt(
                this::numberMatch
            ).sum();
    }

    private int numberMatch(Ball lottoNumber) {
        if (winningNumbers.contains(lottoNumber)){
            return 1;
        }

        return 0;
    }

    public static WinningNumbers createWinningNumbers(String winningNumberStr) {

        Set<Ball> winningNumberSet = Arrays.stream(winningNumberStr.split(","))
            .map(Integer::parseInt)
            .map(Ball::createBall)
            .collect(Collectors.toSet());

        return new WinningNumbers(winningNumberSet);
    }

    public Set<Ball> getWinningNumbers() {
        return winningNumbers;
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
