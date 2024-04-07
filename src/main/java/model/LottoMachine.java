package model;

import java.util.List;

public interface LottoMachine {
    Lotto drawLotto();

    List<Lotto> drawMaximumLottoByMoney(Money money);
}
