package model.calculator;

import java.util.List;
import java.util.Set;


import model.Ball;
import model.winningLottery.Ranking;
import org.junit.jupiter.api.Test;

import model.random.LottoNumbers;
import model.winningLottery.Lottery;
import model.winningLottery.WinningBonusNumber;
import model.winningLottery.WinningNumbers;

import static java.util.Set.*;
import static model.Ball.createBallSet;
import static model.winningLottery.Ranking.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    void 로또_결과_랭킹이_저장된다() {
        LottoNumbers lottoNumbers = new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6)));
        LottoResult lottoResult = new LottoResult(lottoNumbers);

        WinningNumbers winningNumbers = new WinningNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6)));
        WinningBonusNumber bonusNumber = new WinningBonusNumber(new Ball(7));
        Lottery lottery = new Lottery(winningNumbers, bonusNumber);

        Ranking expectedRanking = FIRST;
        lottoResult.saveRanking(lottery);

        assertThat(lottoResult.getRanking()).isEqualTo(expectedRanking);
    }
}