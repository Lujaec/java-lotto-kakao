package model.random;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.Collections.*;
import static java.util.stream.Collectors.*;
import static model.Ball.createBallSet;
import static model.random.NumberRange.*;

import model.Ball;


public interface RandomNumberGenerator {
    List<Integer> lottoNums = IntStream.rangeClosed(START.getValue(), END.getValue()).boxed().collect(toList());

    default Set<Ball> generateRandNums() {
        shuffle(lottoNums);

        return createBallSet(lottoNums
                .stream()
                .limit(6)
                .collect(toSet()));
    }
}
