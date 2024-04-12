package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.HashMap;
import java.util.Map;

public class LottoBallFactory {

    private static final Map<Integer, LottoBall> lottoBalls = new HashMap<>();

    public static LottoBall getLottoBall(int number) {
        if (!lottoBalls.containsKey(number)) {
            lottoBalls.put(number, new LottoBall(number));
        }
        return lottoBalls.get(number);
    }
}