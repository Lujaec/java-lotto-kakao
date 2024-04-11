package model;

import constant.Reward;
import model.vo.PurchaseAmount;
import java.util.*;
import java.util.stream.Collectors;
import static constant.LottoConstants.*;

public class Statistics {
    private Map<Reward, Integer> result;
    private PurchaseAmount purchaseAmount;

    public Statistics(List<UserLotto> userLottos, WinningLotto winningLotto, PurchaseAmount purchaseAmount) {
        generateResultMap();
        this.purchaseAmount = purchaseAmount;
        calculateResult(userLottos, winningLotto);
    }

    private void generateResultMap() {
        result = Arrays.stream(Reward.values())
                .collect(Collectors.toMap(reward -> reward, reward -> 0));
    }

    private void calculateResult(List<UserLotto> userLottos, WinningLotto winningLotto) {
        for (UserLotto userLotto : userLottos) {
            updateResult(userLotto, winningLotto);
        }
    }

    private Reward updateResult(UserLotto userLotto, WinningLotto winningLotto){
        long cnt = winningLotto.getBalls().stream()
                .filter(userLotto.getBalls()::contains)
                .count();

        boolean bonus = userLotto.getBalls().stream()
                .anyMatch(ball -> ball.getNumber() == winningLotto.getBonus().getNumber());

        return updateResult((int)cnt, bonus);
    }

    private Reward updateResult(int correctNumbersCount, boolean bonus){
        Optional<Reward> matchedReward = Arrays.stream(Reward.values())
                .filter(reward -> (reward.getMatchCount() == correctNumbersCount) && (!reward.getBonus() || bonus))
                .findFirst();

        matchedReward
                .ifPresent(reward -> result.put(reward, result.get(reward) + 1));

        return matchedReward.orElse(null);
    }

    public double getStatistics() {
        if (purchaseAmount.getTotalAmount() == ZERO) {
            return 0;
        }

        long rewardAmount = Arrays.stream(Reward.values())
                .mapToLong(reward -> reward.getPrice() * result.get(reward))
                .sum();

        return (double) rewardAmount / (purchaseAmount.getTotalAmount() * LOTTO_PRICE);
    }

    public Map<Reward, Integer> getResult(){
        return Collections.unmodifiableMap(result);
    }
}
