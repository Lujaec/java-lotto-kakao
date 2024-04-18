package com.lotto.util;

import com.lotto.model.LottoNumber;
import com.lotto.model.LottoNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersGenerator implements LottoGenerateStrategy {

    private static final int LOTTO_NUMBER_COUNT = 6;

    @Override
    public LottoNumbers generate() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.values());
        Collections.shuffle(lottoNumbers);
        List<LottoNumber> selectedLottoNumbers = lottoNumbers.stream()
                .limit(LOTTO_NUMBER_COUNT)
                .sorted()
                .collect(Collectors.toList());

        return LottoNumbers.of(selectedLottoNumbers);
    }
}
