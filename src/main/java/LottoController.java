import dtos.LottoDto;
import dtos.LottoResultDto;
import domains.*;
import utils.LottoUtils;

import java.util.List;
import java.util.Scanner;

public class LottoController {
    private final LottoConsoleView lottoConsoleView;

    public LottoController(LottoConsoleView lottoConsoleView) {
        this.lottoConsoleView = lottoConsoleView;
    }

    public void run() {
        Lottos lottos = retriveLottos();
        LottoManager lottoManager = retriveLottoManager(lottos);
        showResult(lottoManager);
    }

    private void showResult(LottoManager lottoManager) {
        LottoResultDto lottoResultDto = LottoResultDto.of(lottoManager.winningResults(), lottoManager.revenueRate());
        lottoConsoleView.printResult(lottoResultDto);
    }

    private LottoManager retriveLottoManager(Lottos lottos) {
        List<Integer> winningNumbersInput = lottoConsoleView.getWinningNumbersInput();
        int bonusBallInput = lottoConsoleView.getBonusBallInput();
        WinningLotto winningLotto = new WinningLotto(Lotto.from(winningNumbersInput), new LottoNumber(bonusBallInput));
        return new LottoManager(lottos, winningLotto);
    }

    private Lottos retriveLottos() {
        int money = lottoConsoleView.getMoneyInput();
        int numberOfManualLottos = lottoConsoleView.getNumberOfManualInput();

        Lottos lottos = issueLotto(money, numberOfManualLottos);
        List<LottoDto> lottoDtos = LottoUtils.convertList(lottos.values(), LottoDto::from);
        lottoConsoleView.printIssuedLottos(lottoDtos, numberOfManualLottos);

        return lottos;
    }

    private Lottos issueLotto(int money, int numberOfManualLottos) {
        LottoInputAmount lottoInputAmount = new LottoInputAmount(money, numberOfManualLottos);
        List<List<Integer>> manualLottoNumbersList = lottoConsoleView.getManualLottoNumbers(numberOfManualLottos);

        return LottoMachine.issue(lottoInputAmount, manualLottoNumbersList);
    }

    public static void main(String[] args) {
        new LottoController(new LottoConsoleView(new Scanner(System.in))).run();
    }
}
