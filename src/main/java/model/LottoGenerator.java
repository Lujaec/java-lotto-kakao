package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    public static final int LOTTO_NUMBERS_COUNT = 6;

    private LottoGenerator() {
        throw new AssertionError("LottoGenerator class cannot be instantiated");
    }

    public static Lottos generateAutoLottos(int autoLottoAmount) {
        List<Lotto> lottos = IntStream.range(0, autoLottoAmount)
                .mapToObj(it -> generateLotto())
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }

    private static Lotto generateLotto() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.LOTTO_NUMBERS);
        Collections.shuffle(lottoNumbers);

        lottoNumbers = lottoNumbers.subList(0, LOTTO_NUMBERS_COUNT);
        Collections.sort(lottoNumbers);

        return new Lotto(lottoNumbers);
    }

    public static Lottos generateManualLottos(List<List<Integer>> manualLottoNumbers) {
        List<Lotto> lottos = manualLottoNumbers.stream()
                .map(Lotto::from)
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }
}
