package model;

public class LottoMachine {
    public UserLotto extractLottoNumbers(Strategy strategy) {
        return strategy.perform();
    }
}
