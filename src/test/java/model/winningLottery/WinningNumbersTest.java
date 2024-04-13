package model.winningLottery;

import java.util.Set;

import model.Ball;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class WinningNumbersTest {

    @Test
    void 동일_숫자가_들어오면_exception() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    WinningNumbers.createBallNumbers("1,1,2,3,4,5");
                }).withMessage("서로 다른 6개 숫자를 입력해주세요");
    }

    @Test
    void 여섯_글자_넘으면_exception() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    WinningNumbers.createBallNumbers("1,2,3,4,5,6,7");
                }).withMessage("서로 다른 6개 숫자를 입력해주세요");
    }

    @Test
    void 범위_넘어간_숫자_exception() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    WinningNumbers.createBallNumbers("1,2,46,4,5,6");
                }).withMessage("1 ~ 45 사이 값을 입력하세요");
    }

    @Test
    void 생성_테스트() {
        WinningNumbers winningNumbers = WinningNumbers.createBallNumbers("1,2,3,4,5,6");
        WinningNumbers expectedWinningNumber = new WinningNumbers(Ball.createBallSet(Set.of(1, 2, 3, 4, 5, 6)));

        assertThat(winningNumbers).isEqualTo(expectedWinningNumber);
    }
}