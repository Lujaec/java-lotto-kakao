package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public LottoResult calculateResult(WinningLotto winningLotto) {
        List<LottoRank> rottoRanks = lottos.stream()
                .map(winningLotto::makeLottoRank)
                .collect(Collectors.toList());

        return new LottoResult(rottoRanks);
    }

    public Lottos merge(Lottos other) {
        List<Lotto> mergedLottos = new ArrayList<>();
        mergedLottos.addAll(lottos);
        mergedLottos.addAll(other.lottos);
        return new Lottos(mergedLottos);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
