package model.calculator;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Ball;
import model.winningLottery.*;
import org.junit.jupiter.api.Test;

import model.random.LottoNumbers;

import static model.Ball.createBallSet;
import static model.winningLottery.Ranking.FIFTH;
import static model.winningLottery.Ranking.FIRST;
import static model.winningLottery.Ranking.FOURTH;
import static model.winningLottery.Ranking.NONE;
import static model.winningLottery.Ranking.SECOND;
import static model.winningLottery.Ranking.THIRD;
import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @Test
    void 로또번호들을_저장한다() {
        LottoNumbers lottoNumbersFirst = new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6)));
        LottoResult firstOne = new LottoResult(lottoNumbersFirst);

        WinningNumbers winningNumbers = new WinningNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6)));
        WinningBonusNumber bonusNumber = new WinningBonusNumber(new Ball(7));

        Lottery lottery = new Lottery(winningNumbers, bonusNumber);

        Calculator calculator = new Calculator(List.of(firstOne));

        calculator.saveRanking(lottery);

        Map<Ranking, Integer> expectedResult = new EnumMap<>(Ranking.class);
        expectedResult.put(FIRST, 1);
        expectedResult.put(SECOND, 0);
        expectedResult.put(THIRD, 0);
        expectedResult.put(FOURTH, 0);
        expectedResult.put(FIFTH, 0);
        expectedResult.put(NONE, 0);

        assertThat(calculator.getRankingCountMap()).isEqualTo(expectedResult);

    }

    @Test()
    void 각_등수를_가진_로또번호들의_갯수를구한다() {
        LottoNumbers lottoNumbersFirst = new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6)));
        LottoNumbers lottoNumbersSecond = new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 7)));

        LottoResult firstOne = new LottoResult(lottoNumbersFirst);
        LottoResult firstTwo = new LottoResult(lottoNumbersFirst);
        LottoResult secondOne = new LottoResult(lottoNumbersSecond);

        WinningNumbers winningNumbers = new WinningNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6)));
        WinningBonusNumber bonusNumber = new WinningBonusNumber(new Ball(7));

        Lottery lottery = new Lottery(winningNumbers, bonusNumber);

        firstOne.saveRanking(lottery);
        firstTwo.saveRanking(lottery);
        secondOne.saveRanking(lottery);

        Calculator calculator = new Calculator(List.of(firstOne, firstTwo, secondOne));

        Map<Ranking, Integer> expectedEnumMap = new EnumMap<>(Ranking.class);
        expectedEnumMap.put(FIRST, 2);
        expectedEnumMap.put(SECOND, 1);
        expectedEnumMap.put(THIRD, 0);
        expectedEnumMap.put(FOURTH, 0);
        expectedEnumMap.put(FIFTH, 0);
        expectedEnumMap.put(NONE, 0);

        calculator.calculateRankingCount();
        Map<Ranking, Integer> rankingCountMap = calculator.getRankingCountMap();

        assertThat(rankingCountMap).isEqualTo(expectedEnumMap);
    }

    @Test()
    void 각_등수를_통해_전체_수익률을_구한다() {
        //given
        LottoNumbers lottoNumbersFirst = new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6)));
        LottoNumbers lottoNumbersSecond = new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 7)));

        LottoResult firstOne = new LottoResult(lottoNumbersFirst);
        LottoResult firstTwo = new LottoResult(lottoNumbersFirst);
        LottoResult secondOne = new LottoResult(lottoNumbersSecond);

        WinningNumbers winningNumbers = new WinningNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6)));
        WinningBonusNumber bonusNumber = new WinningBonusNumber(new Ball(7));

        Lottery lottery = new Lottery(winningNumbers, bonusNumber);

        firstOne.saveRanking(lottery);
        firstTwo.saveRanking(lottery);
        secondOne.saveRanking(lottery);

        Calculator calculator = new Calculator(List.of(firstOne, firstTwo, secondOne));

        calculator.calculateRankingCount();
        double profitRate = calculator.calculateProfitRate();

        assertThat(profitRate).isEqualTo(1343333.33);
    }

}