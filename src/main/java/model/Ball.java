package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Ball {
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;
    public static List<Ball> lottoNumbers = IntStream.rangeClosed(START_NUMBER, END_NUMBER).mapToObj(Ball::new).collect(toList());

    private final int ball;

    private Ball(int ballNumber) {
        this.ball = ballNumber;
    }

    public static Ball createBall(int ballNumber) {
        if (ballNumber < START_NUMBER || ballNumber > END_NUMBER) {
            throw new IllegalArgumentException(String.format("%d ~ %d 사이 값을 입력하세요",
                START_NUMBER, END_NUMBER));
        }
        
        return lottoNumbers.get(ballNumber - 1);
    }

    public static Set<Ball> createBalls(Set<Integer> balls) {
        return balls.stream()
            .map(Ball::createBall)
            .collect(toSet());
    }

    public static List<Ball> getBalls() {
        return new ArrayList<>(lottoNumbers);
    }

    public int getBall() {
        return ball;
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
