package domain;

import java.util.List;

public class ManualLottoGenerator implements LottoGenerator {

    private final List<Lotto> manualLottos;

    public ManualLottoGenerator(List<Lotto> manualNumbers) {
        this.manualLottos = manualNumbers;
    }

    @Override
    public Lottos generateLottos(int lottoCount) {
        if (manualLottos.size() != lottoCount) {
            throw new IllegalArgumentException(lottoCount + "개의 로또 번호를 입력해주세요.");
        }
        return new Lottos(manualLottos);
    }
}
