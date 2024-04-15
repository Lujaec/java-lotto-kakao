package lotto.dto;

import java.util.List;

public class LottoResultDto {
    private final List<PrizeResultDto> lottoResult;
    private final double resultRate;

    public LottoResultDto(List<PrizeResultDto> lottoResult, double resultRate) {
        this.lottoResult = lottoResult;
        this.resultRate = resultRate;
    }

    public List<PrizeResultDto> getLottoResult() {
        return lottoResult;
    }

    public double getResultRate() {
        return resultRate;
    }
}
