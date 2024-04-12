package domain;

public class WinningLotto {
    private final Lotto winnigLotto;
    private final LottoBall bonusNumber;

    public WinningLotto(Lotto winnigLotto, LottoBall bonusNumber) {
        if (winnigLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복되면 안됩니다.");
        }
        this.winnigLotto = winnigLotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank getRank(Lotto lotto) {
        int matchCount = lotto.getMatchCount(winnigLotto);
        boolean matchBonus = lotto.contains(bonusNumber);
        return Rank.findRank(matchCount, matchBonus);
    }
}
