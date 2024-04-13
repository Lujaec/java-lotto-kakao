package application;

import controller.LottoController;

public class LottoApplication {
    public static void main(String[] args) {
        LottoController lottoGameController = new LottoController();
        lottoGameController.run();
    }
}
