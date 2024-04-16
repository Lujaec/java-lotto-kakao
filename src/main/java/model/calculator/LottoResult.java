package model.calculator;

import java.util.Objects;

import model.LottoNumbers;
import model.winningLottery.WinMatch;
import model.winningLottery.Ranking;

public class LottoResult {
    private final LottoNumbers lottoNumbers;
    private final Ranking ranking;

    private LottoResult(LottoNumbers lottoNumbers, Ranking ranking) {
        this.lottoNumbers = lottoNumbers;
        this.ranking = ranking;
    }

    public static LottoResult createLottoResult(LottoNumbers lottoNumbers, WinMatch winMatch) {
        Ranking rank = winMatch.rank(lottoNumbers);
        return new LottoResult(lottoNumbers, rank);
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    public Ranking getRanking() {
        return ranking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult that = (LottoResult) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers) && ranking == that.ranking;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers, ranking);
    }
}
