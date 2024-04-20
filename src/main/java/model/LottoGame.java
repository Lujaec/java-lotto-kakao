package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.NumberGenerator;

public class LottoGame {

    private final Cost cost;
    private final Lottos lottos;

    public LottoGame(int cost, int manualLottoAmount, NumberGenerator manualNumberGenerator,
        NumberGenerator randomNumberGenerator) {
        this.cost = new Cost(cost);
        int amounts = calculateLottoAmount() - manualLottoAmount;
        lottos = new Lottos(amounts, randomNumberGenerator);
        lottos.add(
            new Lottos(manualLottoAmount, manualNumberGenerator)
        );
    }

    public int calculateLottoAmount() {
        return cost.calculateLottoAmount();
    }

    public List<LottoRank> calculateResult(WinnerLottoNumber winnerLottoNumber) {
        return winnerLottoNumber.calculateRanks(lottos);
    }

    public Map<LottoRank, Integer> calculateStatistics(List<LottoRank> lottoRanks) {
        Map<LottoRank, Integer> statistics = new HashMap<>();
        for (LottoRank lottoRank : lottoRanks) {
            statistics.put(lottoRank, statistics.getOrDefault(lottoRank, 0) + 1);
        }
        return statistics;
    }

    public Lottos getLottos() {
        return lottos;
    }

    public Double calculateProfit(List<LottoRank> lottoRanks) {
        return lottoRanks.stream()
            .mapToDouble(LottoRank::getPrize)
            .sum() / cost.getSpent();
    }
}
