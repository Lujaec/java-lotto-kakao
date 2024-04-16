package model.winningLottery;

import java.util.Set;

import org.junit.jupiter.api.Test;

import model.Ball;
import model.LottoNumbers;

import static model.winningLottery.Ranking.FIFTH;
import static model.winningLottery.Ranking.FIRST;
import static model.winningLottery.Ranking.FOURTH;
import static model.winningLottery.Ranking.NONE;
import static model.winningLottery.Ranking.SECOND;
import static model.winningLottery.Ranking.THIRD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class WinMatchTest {

    @Test
    void 당첨번호와_보너스숫자를_입력받아_몇등인지_구한다_1등() {
        WinningNumbers winningNumbers = WinningNumbers.createWinningNumbers("1,2,3,4,5,6");
        WinningBonusNumber winningBonusNumber = new WinningBonusNumber(Ball.createBall(7));

        WinMatch winMatch = new WinMatch(winningNumbers, winningBonusNumber);
        Ranking rank = winMatch.rank(new LottoNumbers(Ball.createBalls(Set.of(1, 2, 3, 4, 5, 6))));

        assertThat(rank).isEqualTo(FIRST);
    }

    @Test
    void 당첨번호와_보너스숫자를_입력받아_몇등인지_구한다_2등() {
        WinningNumbers winningNumbers = WinningNumbers.createWinningNumbers("1,2,3,4,5,6");
        WinningBonusNumber winningBonusNumber = new WinningBonusNumber(Ball.createBall(7));

        WinMatch winMatch = new WinMatch(winningNumbers, winningBonusNumber);
        Ranking rank = winMatch.rank(new LottoNumbers(Ball.createBalls(Set.of(1, 2, 7, 4, 5, 6))));

        assertThat(rank).isEqualTo(SECOND);
    }

    @Test
    void 당첨번호와_보너스숫자를_입력받아_몇등인지_구한다_3등() {
        WinningNumbers winningNumbers = WinningNumbers.createWinningNumbers("1,2,3,4,5,6");
        WinningBonusNumber winningBonusNumber = new WinningBonusNumber(Ball.createBall(7));

        WinMatch winMatch = new WinMatch(winningNumbers, winningBonusNumber);
        Ranking rank = winMatch.rank(new LottoNumbers(Ball.createBalls(Set.of(1, 2, 3, 4, 5, 8))));

        assertThat(rank).isEqualTo(THIRD);
    }

    @Test
    void 당첨번호와_보너스숫자를_입력받아_몇등인지_구한다_4등() {
        WinningNumbers winningNumbers = WinningNumbers.createWinningNumbers("1,2,3,4,5,6");
        WinningBonusNumber winningBonusNumber = new WinningBonusNumber(Ball.createBall(7));

        WinMatch winMatch = new WinMatch(winningNumbers, winningBonusNumber);
        Ranking rank = winMatch.rank(new LottoNumbers(Ball.createBalls(Set.of(1, 2, 3, 9, 5, 8))));

        assertThat(rank).isEqualTo(FOURTH);
    }

    @Test
    void 당첨번호와_보너스숫자를_입력받아_몇등인지_구한다_5등() {
        WinningNumbers winningNumbers = WinningNumbers.createWinningNumbers("1,2,3,4,5,6");
        WinningBonusNumber winningBonusNumber = new WinningBonusNumber(Ball.createBall(7));

        WinMatch winMatch = new WinMatch(winningNumbers, winningBonusNumber);
        Ranking rank = winMatch.rank(new LottoNumbers(Ball.createBalls(Set.of(1, 2, 3, 9, 10, 8))));

        assertThat(rank).isEqualTo(FIFTH);
    }

    @Test
    void 당첨번호와_보너스숫자를_입력받아_몇등인지_구한다_그외1() {
        WinningNumbers winningNumbers = WinningNumbers.createWinningNumbers("1,2,3,4,5,6");
        WinningBonusNumber winningBonusNumber = new WinningBonusNumber(Ball.createBall(7));

        WinMatch winMatch = new WinMatch(winningNumbers, winningBonusNumber);
        Ranking rank = winMatch.rank(new LottoNumbers(Ball.createBalls(Set.of(1, 2, 11, 9, 10, 8))));

        assertThat(rank).isEqualTo(NONE);
    }

    @Test
    void 당첨번호와_보너스숫자를_입력받아_몇등인지_구한다_그외2() {
        WinningNumbers winningNumbers = WinningNumbers.createWinningNumbers("1,2,3,4,5,6");
        WinningBonusNumber winningBonusNumber = new WinningBonusNumber(Ball.createBall(7));

        WinMatch winMatch = new WinMatch(winningNumbers, winningBonusNumber);
        Ranking rank = winMatch.rank(new LottoNumbers(Ball.createBalls(Set.of(1, 12, 11, 9, 10, 8))));

        assertThat(rank).isEqualTo(NONE);
    }

    @Test
    void 당첨번호와_보너스숫자를_입력받아_몇등인지_구한다_그외3() {
        WinningNumbers winningNumbers = WinningNumbers.createWinningNumbers("1,2,3,4,5,6");
        WinningBonusNumber winningBonusNumber = new WinningBonusNumber(Ball.createBall(7));

        WinMatch winMatch = new WinMatch(winningNumbers, winningBonusNumber);
        Ranking rank = winMatch.rank(new LottoNumbers(Ball.createBalls(Set.of(13, 2, 11, 9, 10, 8))));

        assertThat(rank).isEqualTo(NONE);
    }


    @Test
    void 보너스넘버_지난주당첨번호_있으면_exception() {

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                WinningNumbers winningNumbers = WinningNumbers.createWinningNumbers("1,2,3,4,5,6");
                WinningBonusNumber winningBonusNumber = new WinningBonusNumber(Ball.createBall(6));
                new WinMatch(winningNumbers, winningBonusNumber);
            }).withMessage("지난주 당첨 번호와 보너스 번호는 달라야 합니다.");
    }

    @Test
    void 보너스넘버_범위_넘어가면_exception() {

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                WinningNumbers winningNumbers = WinningNumbers.createWinningNumbers("1,2,3,4,5,6");
                WinningBonusNumber winningBonusNumber = new WinningBonusNumber(Ball.createBall(46));
                new WinMatch(winningNumbers, winningBonusNumber);
            }).withMessage("1 ~ 45 사이 값을 입력하세요");
    }

    @Test
    void 동일_숫자가_들어오면_exception() {

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                WinningNumbers winningNumbers = WinningNumbers.createWinningNumbers("1,1,2,3,4,5");
                WinningBonusNumber winningBonusNumber = new WinningBonusNumber(Ball.createBall(45));
                new WinMatch(winningNumbers, winningBonusNumber);
            }).withMessage("서로 다른 6개 숫자를 입력해주세요");
    }

    @Test
    void 여섯_글자_넘으면_exception() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                WinningNumbers winningNumbers = WinningNumbers.createWinningNumbers("1,2,3,4,5,6,7");
                WinningBonusNumber winningBonusNumber = new WinningBonusNumber(Ball.createBall(45));
                new WinMatch(winningNumbers, winningBonusNumber);
            }).withMessage("서로 다른 6개 숫자를 입력해주세요");
    }

    @Test
    void 범위_넘어간_숫자_exception() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                WinningNumbers winningNumbers = WinningNumbers.createWinningNumbers("1,2,3,4,5,46");
                WinningBonusNumber winningBonusNumber = new WinningBonusNumber(Ball.createBall(45));
                new WinMatch(winningNumbers, winningBonusNumber);
            }).withMessage("1 ~ 45 사이 값을 입력하세요");
    }
}