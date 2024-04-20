package model;

import java.util.List;
import java.util.stream.Collectors;

public class WinnerLottoNumber {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    private WinnerLottoNumber(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = LottoNumber.of(bonusNumber);
    }

    public static WinnerLottoNumber of(List<Integer> numbers, int bonusNumber) {
        return new WinnerLottoNumber(Lotto.of(numbers), bonusNumber);
    }

    public List<LottoRank> calculateRanks(Lottos lottos) {
        return lottos.getLottos().stream()
            .map(lotto -> LottoRank.of(getMatchCount(lotto), getMatchBonus(lotto)))
            .collect(Collectors.toList());
    }

    private int getMatchCount(Lotto lotto) {
        return this.lotto.getMatchCount(lotto);
    }

    private boolean getMatchBonus(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
