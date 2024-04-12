package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoBall implements Comparable<LottoBall> {

    public static final int LOTTO_UPPER_BOUND = 45;
    public static final int LOTTO_LOWER_BOUND = 1;

    private final int lottoBall;
    private static final List<LottoBall> cachedLottoBalls = IntStream.rangeClosed(LOTTO_LOWER_BOUND, LOTTO_UPPER_BOUND)
            .mapToObj(LottoBall::new)
            .collect(Collectors.toUnmodifiableList());

    public LottoBall(int lottoBall) {
        validateLottoBall(lottoBall);
        this.lottoBall = lottoBall;
    }

    public static LottoBall valueOf(int lottoBall) {
        validateLottoBall(lottoBall);
        return cachedLottoBalls.get(lottoBall - LOTTO_LOWER_BOUND);
    }

    public static Lotto toLotto(int... lottoBalls) {
        return new Lotto(Arrays.stream(lottoBalls)
                .mapToObj(LottoBall::valueOf)
                .collect(Collectors.toList()));
    }

    private static void validateLottoBall(int lottoBall) {
        if (lottoBall < LOTTO_LOWER_BOUND || lottoBall > LOTTO_UPPER_BOUND) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 까지여야 합니다.");
        }
    }

    public int getLottoBall() {
        return lottoBall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoBall lottoBall1 = (LottoBall)o;
        return lottoBall == lottoBall1.lottoBall;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoBall);
    }

    @Override
    public String toString() {
        return String.valueOf(this.lottoBall);
    }

    @Override
    public int compareTo(LottoBall lottoBall) {
        return Integer.compare(this.lottoBall, lottoBall.lottoBall);
    }
}
