package lotto.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CustomAutoGenerator implements AutoGenerator {

    private final LinkedList<List<Integer>> lottoGroups;

    @SafeVarargs
    public CustomAutoGenerator(final List<Integer>... groupNumbers) {
        this.lottoGroups = new LinkedList<>(Arrays.asList(groupNumbers));
    }

    @Override
    public List<Integer> generate() {
        return lottoGroups.remove();
    }
}
