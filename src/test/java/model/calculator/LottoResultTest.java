package model.calculator;

import java.util.Set;

import org.junit.jupiter.api.Test;

import model.Ball;
import model.LottoNumbers;
import model.winningLottery.WinMatch;
import model.winningLottery.WinningBonusNumber;
import model.winningLottery.WinningNumbers;

import static model.winningLottery.Ranking.*;
import static org.assertj.core.api.Assertions.assertThat;


class LottoResultTest {

    @Test
    void 로또_결과_랭킹이_저장된다() {
        LottoNumbers lottoNumbers = new LottoNumbers(Ball.createBalls(Set.of(1,2,3,4,5,6)));

        WinningNumbers winningNumbers = WinningNumbers.createWinningNumbers("1,2,3,4,5,6");
        WinningBonusNumber bonusNumber = new WinningBonusNumber(Ball.createBall(7));
        WinMatch winMatch = new WinMatch(winningNumbers, bonusNumber);

        LottoResult lottoResult = LottoResult.createLottoResult(lottoNumbers, winMatch);

        assertThat(lottoResult.getRanking()).isEqualTo(FIRST);
    }
}