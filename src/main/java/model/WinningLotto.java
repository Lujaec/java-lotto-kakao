package model;

import java.util.List;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusLottoNumber;

    public WinningLotto(List<Integer> winningLottoNumbers, int bonusNumber) {
        this(Lotto.from(winningLottoNumbers), LottoNumber.valueOf(bonusNumber));
    }

    public WinningLotto(Lotto lotto, LottoNumber bonusLottoNumber) {
        validationWinningLotto(lotto, bonusLottoNumber);
        this.lotto = lotto;
        this.bonusLottoNumber = bonusLottoNumber;
    }

    private void validationWinningLotto(Lotto inputLotto, LottoNumber bonusNumber) {
        if (inputLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 숫자는 입력되지 않은 로또 숫자만 가능합니다.");
        }
    }

    public LottoRank makeLottoRank(Lotto lotto) {
        int matchCount = getMatchCount(lotto);
        boolean matchBonus = checkLottoContainsBonusNumber(lotto);
        return LottoRank.of(matchCount, matchBonus);
    }

    private int getMatchCount(Lotto lotto) {
        return this.lotto.getMatchCount(lotto);
    }

    private boolean checkLottoContainsBonusNumber(Lotto lotto) {
        return lotto.contains(bonusLottoNumber);
    }
}
