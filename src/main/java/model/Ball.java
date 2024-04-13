package model;

import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.*;
import static model.random.NumberRange.END;
import static model.random.NumberRange.START;

public class Ball {
    private final int ball;

    public Ball(int ball) {
        validateNumberRange(ball);
        this.ball = ball;
    }

    public String toString() {
        return Integer.toString(ball);
    }

    public static Set<Ball> createBallSet(Set<Integer> balls) {
        return balls.stream()
                .map(Ball::new)
                .collect(toSet());
    }

    public int getBall() {
        return ball;
    }

    private void validateNumberRange(int ballNumber) {
        if (ballNumber < START.getValue() ||
                ballNumber > END.getValue()) {
            throw new IllegalArgumentException(String.format("%d ~ %d 사이 값을 입력하세요",
                    START.getValue(), END.getValue()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball1 = (Ball) o;
        return ball == ball1.ball;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ball);
    }
}
