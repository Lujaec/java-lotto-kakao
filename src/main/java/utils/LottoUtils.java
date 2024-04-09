package utils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoUtils {
    private LottoUtils() {
    }

    public static <T, R> List<R> convertList(List<T> list, Function<? super T, ? extends R> function) {
        return list.stream()
                .map(function)
                .collect(Collectors.toList());
    }
}
