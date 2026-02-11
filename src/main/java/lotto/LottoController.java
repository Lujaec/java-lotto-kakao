package lotto;

public class LottoController {
	private final InputView inputView;
	private final OutputView outputView;

	public LottoController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		Money purchaseMoney = readPurchaseMoney();
		LottoTickets lottoTickets = generateLottoTickets(purchaseMoney);
		outputView.printPurchaseResult(lottoTickets);
		LottoTicket winningTicket = readWinningTicket();
		LottoAnswer lottoAnswer = readLottoAnswer(winningTicket);
		LottoStatistics lottoStatistics = lottoTickets.buildStatistics(lottoAnswer);
		outputView.printStatistics(lottoStatistics, purchaseMoney);
	}

	private Money readPurchaseMoney() {
		try {
			return inputView.readMoney();
		} catch (IllegalArgumentException exception) {
			outputView.printError(exception.getMessage());
			return readPurchaseMoney();
		}
	}

	private LottoTickets generateLottoTickets(Money purchaseMoney) {
		return LottoTicketGenerator.generate(purchaseMoney.toPurchaseCount());
	}

	private LottoTicket readWinningTicket() {
		try {
			return inputView.readWinningNumbers();
		} catch (IllegalArgumentException exception) {
			outputView.printError(exception.getMessage());
			return readWinningTicket();
		}
	}

	private LottoAnswer readLottoAnswer(LottoTicket winningTicket) {
		try {
			return new LottoAnswer(winningTicket, inputView.readBonusNumber());
		} catch (IllegalArgumentException exception) {
			outputView.printError(exception.getMessage());
			return readLottoAnswer(winningTicket);
		}
	}

}
