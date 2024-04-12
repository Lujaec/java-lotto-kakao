package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public WinningResult calculateWinningResult(WinningLotto winningLotto) {
        return new WinningResult(lottos.stream()
                .collect(Collectors.groupingBy(winningLotto::getRank, Collectors.counting())));
    }

    public void merge(Lottos addedLottos) {
        lottos.addAll(addedLottos.lottos);
    }

    public int getLottoCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
