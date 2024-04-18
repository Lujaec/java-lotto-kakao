package com.lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

    private StringUtils() {
    }

    public static List<Integer> parseCommaSeparatedToIntegerList(String string) {
        return Arrays.stream(string.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
