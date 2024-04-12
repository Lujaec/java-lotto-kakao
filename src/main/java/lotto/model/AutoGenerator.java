package lotto.model;

import java.util.List;

@FunctionalInterface
public interface AutoGenerator {

    List<Integer> generate();
}
