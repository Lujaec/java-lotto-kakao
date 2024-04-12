package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoGroup {

    private static final int BALLS_SIZE = 6;
    private static final String INVALID_BALLS_SIZE_MESSAGE = "로또 볼은 6개가 존재해야 합니다.";
    private static final String DUPLICATED_BALLS_MESSAGE = "로또 그룹에서 볼은 중복될 수 없습니다.";
    private static final int ONE = 1;

    private final Set<LottoBall> balls;

    public LottoGroup(final List<Integer> numbers) {
        validateNumbersSize(numbers);

        this.balls = numbers.stream()
                .map(LottoBallFactory::getLottoBall)
                .collect(Collectors.toUnmodifiableSet());

        validateDuplicateBalls();
    }

    private void validateNumbersSize(final List<Integer> numbers) {
        if (numbers.size() != BALLS_SIZE) {
            throw new IllegalArgumentException(INVALID_BALLS_SIZE_MESSAGE);
        }
    }

    private void validateDuplicateBalls() {
        if (balls.size() != BALLS_SIZE) {
            throw new IllegalArgumentException(DUPLICATED_BALLS_MESSAGE);
        }
    }

    public boolean containsBall(final LottoBall ball) {
        return balls.contains(ball);
    }

    public int countMatch(final LottoGroup otherBalls) {
        return this.balls.stream()
                .filter(otherBalls::containsBall)
                .mapToInt(match -> ONE)
                .sum();
    }

    public List<Integer> getLottoBallNumbers() {
        return balls.stream()
                .map(LottoBall::getNumber)
                .collect(Collectors.toUnmodifiableList());
    }
}
