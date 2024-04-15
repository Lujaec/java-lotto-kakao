import domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import view.InputView;
import view.OutputView;

public class LottoMain {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        LottoTickets lottoTickets = buyLottos();
        outputView.printUserLottos(extractLottoNumbersPerTicket(lottoTickets));

        outputView.printWinningNumbersGuide();
        String winningNumbers = inputView.inputString();
        outputView.printBonusBallGuide();
        int bonusBall = inputView.inputInt();

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusBall);
        noticeResult(winningLotto, lottoTickets);
    }

    private static List<List<Integer>> extractLottoNumbersPerTicket(LottoTickets lottoTickets) {
        return lottoTickets.getLottoTicketList()
                .stream()
                .map(LottoTicket::getLottoNumbers)
                .collect(Collectors.toList());
    }

    private static LottoTickets buyLottos() {
        outputView.printGameGuide();
        int money = inputView.inputInt();
        inputView.inputString();

        LottoStore lottoStore = new LottoStore(money);

        int manualCount = getManualCount(lottoStore);
        List<String> manualList = getManualLottos(manualCount);

        outputView.printLottoCount(manualCount, lottoStore.getLottoCount() - manualCount);

        return lottoStore.buyLottoTickets(new LottoNumberGenerator(), manualList);
    }

    private static int getManualCount(LottoStore lottoStore) {
        outputView.printManualCountGuide();
        int manualCount = inputView.inputInt();
        inputView.inputString();

        lottoStore.validateManualCount(manualCount);
        return manualCount;
    }

    private static List<String> getManualLottos(int manualCount) {
        outputView.printManualLottoGuide();

        ArrayList<String> lottoTickets = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            lottoTickets.add(inputView.inputString());
        }

        return lottoTickets;
    }

    private static void noticeResult(WinningLotto winningLotto, LottoTickets lottoTickets) {
        LottoGame lottoGame = new LottoGame(winningLotto, lottoTickets);
        outputView.printStatistics(lottoGame.getRank());
        outputView.printRevenue(lottoGame.calculateRevenuePercent());
    }
}
