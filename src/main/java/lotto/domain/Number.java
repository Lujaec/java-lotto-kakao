package lotto.domain;

import static lotto.domain.LottoConstants.*;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Objects;

public class Number {
    private static Number[] numberInstances = null;
    private final int number;
    private Number(int number) {
        this.number = number;
    }
    public static Number getNumberInstance(int number) {
        if (numberInstances == null) {
            numberInstances = new Number[NUMBER_MAX - NUMBER_MIN + 1];
            initiateNumberInstances();
        }

        validateBall(number);
        if (numberInstances[number - 1] == null) {
            numberInstances[number - 1] = new Number(number);
        }
        return numberInstances[number - 1];
    }

    private static void initiateNumberInstances() {
        for (int i = NUMBER_MIN; i < NUMBER_MAX+1; i++) {
            numberInstances[i - 1] = new Number(i);
        }
    }

    private static void validateBall(int number) {
        if (number < NUMBER_MIN) {
            throw new IllegalArgumentException("공은 "+NUMBER_MIN+"이상의 정수여야 합니다.");
        }

        if (number > NUMBER_MAX) {
            throw new IllegalArgumentException("공은 "+NUMBER_MAX+"이하의 정수여야 합니다.");
        }
    }

    public Integer toInteger() {
        return number;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Number number = (Number)other;
        return this.number == number.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
