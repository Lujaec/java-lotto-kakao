package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManualLotto {

    private final List<LottoTicket> lottos;

    public ManualLotto(List<String> lottoNumbers) {
        this.lottos = lottoNumbers.stream()
            .map(LottoTicket::new)
            .collect(Collectors.toList());
    }

    public int getLottoSize() {
        return lottos.size();
    }

    public List<LottoTicket> getLottos() {
        return new ArrayList<>(lottos);
    }
}
