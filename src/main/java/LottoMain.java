import domain.LottoGame;
import domain.LottoStore;
import domain.LottoTicket;
import domain.Money;
import domain.Quantity;
import domain.WinningLotto;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import view.InputView;
import view.OutputView;

public class LottoMain {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        List<LottoTicket> lottoTickets = buyLottos();
        outputView.printUserLottos(
            lottoTickets.stream()
                .map(LottoTicket::getLottoNumbers)
                .collect(Collectors.toList())
        );

        outputView.printWinningNumbersGuide();
        String winningNumbers = inputView.inputString();
        outputView.printBonusBallGuide();
        int bonusBall = inputView.inputInt();

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusBall);
        noticeResult(winningLotto, lottoTickets);
    }

    private static List<LottoTicket> buyLottos() {
        outputView.printGameGuide();
        Money money = new Money(inputView.inputInt());
        inputView.inputString();

        LottoStore lottoStore = new LottoStore(money);
        buyManualLottos(lottoStore);

        lottoStore.buyAutoLottos(new LottoNumberGenerator());
        outputView.printLottoCount(lottoStore.getManualLottoCount(), lottoStore.getAutoLottoCount());

        return lottoStore.getLottos();
    }

    private static void buyManualLottos(LottoStore lottoStore) {
        outputView.printManualLottoCount();
        Quantity manualLottoCount = new Quantity(inputView.inputInt());
        inputView.inputString();

        outputView.printManualLottoNumbers();
        List<String> manualLottoNumbers = LongStream.range(0, manualLottoCount.getValue())
            .mapToObj(value -> inputView.inputString())
            .collect(Collectors.toList());

        lottoStore.buyManualLottos(manualLottoNumbers);
    }

    private static void noticeResult(WinningLotto winningLotto, List<LottoTicket> lottoTickets) {
        LottoGame lottoGame = new LottoGame(winningLotto, lottoTickets);
        outputView.printStatistics(lottoGame.getRank());
        outputView.printRevenue(lottoGame.calculateRevenue());
    }
}
