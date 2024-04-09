package domains;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<WinningResult> checkWinning(WinningLotto winningLotto) {
        return lottos.stream()
                .map(lotto -> WinningResult.of(lotto, winningLotto))
                .collect(Collectors.toList());
    }

    public List<Lotto> values() {
        return new ArrayList<>(lottos);
    }
}
