package com.lotto.model;

import com.lotto.util.LottoGenerateStrategy;

public class AutoLottoMachine {

    private final LottoGenerateStrategy lottoGenerateStrategy;

    public AutoLottoMachine(LottoGenerateStrategy lottoGenerateStrategy) {
        this.lottoGenerateStrategy = lottoGenerateStrategy;
    }

    public LottoNumbers generateLottoNumbers() {
        return lottoGenerateStrategy.generate();
    }
}
