package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private List<UserLotto> userLottos;

    public LottoController(List<UserLotto> userLottos) {
        this.userLottos = userLottos;
    }

    public List<UserLotto> getUserLottos() {
        return new ArrayList<>(userLottos);
    }

}
