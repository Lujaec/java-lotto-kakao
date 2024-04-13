package model.random;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import model.Ball;

import static model.winningLottery.WinningNumbers.validateBall;


public class LottoNumbers {
    private final Set<Ball> LottoNumbers;

    public LottoNumbers(Set<Ball> lottoNumbers) {
        LottoNumbers = lottoNumbers;
    }

    public static Set<Ball> createBallNumbers(String ballNumberStr) {
        Set<Ball> ballNumberSet = Arrays.stream(ballNumberStr.replace(" ", "").split(","))
                .filter(Objects::nonNull)
                .map(model.random.LottoNumbers::changer).filter(Objects::nonNull)
                .map(Ball::new)
                .collect(Collectors.toSet());

        if (ballNumberSet.isEmpty()) {
            return null;
        }

        validateBall(ballNumberSet);

        return ballNumberSet;
    }

    private static Integer changer(String str) {
        if (str.isBlank() || str.isEmpty()) {
            return null;
        }
        return Integer.parseInt(str);
    }

    public Set<Ball> getLottoNumbers() {
        return LottoNumbers;
    }

    public static Set<Ball> generateRandNums(RandomNumberGenerator randomNumberGenerator) {
        return randomNumberGenerator.generateRandNums();
    }
}
