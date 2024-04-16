package model;

import java.util.List;
import java.util.Set;

import model.Ball;

import static java.util.Collections.*;
import static java.util.stream.Collectors.*;
import static model.Ball.*;

public class RandomNumberGenerator {

    public static Set<Ball> generateRandomNumbers() {
        List<Ball> balls = getBalls();
        shuffle(balls);

        return balls
            .stream()
            .limit(6)
            .collect(toSet());
    }
}
