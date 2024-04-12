package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class AutoLotto {

    private final List<LottoTicket> lottos;

    public AutoLotto(long count, NumberGenerator numberGenerator) {
        this.lottos = LongStream.range(0, count)
            .mapToObj(number -> new LottoTicket(numberGenerator.generateNumbers()))
            .collect(Collectors.toList());
    }

    public int getLottoSize() {
        return lottos.size();
    }

    public List<LottoTicket> getLottos() {
        return new ArrayList<>(lottos);
    }
}
