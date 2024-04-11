package controller;

import java.util.stream.Collectors;

import domain.Cash;
import domain.LottoGameResult;
import domain.LottoNumber;
import domain.LottoTicket;
import domain.LottoTicketGenerator;
import domain.LottoTickets;
import domain.WinningLottoTicket;
import ui.LottoView;

public class LottoController {

	public void play() {
		LottoTickets lottoTickets = buyLottoTickets();

		WinningLottoTicket lottoWinningNumbers = inputLottoWinningTicket();

		LottoGameResult lottoGameResult = runLottoGame(lottoTickets, lottoWinningNumbers);

		displayLottoGameResult(lottoGameResult);
	}

	private static LottoTickets buyLottoTickets() {
		Cash cash = LottoView.getCash();
		int countOfManualTickets = LottoView.getCountOfManualTickets();
		cash.purchaseLottoTickets(countOfManualTickets);
		LottoTickets manualLottoTickets = createManualTickets(countOfManualTickets);

		int countOfAutoLottoTickets = cash.getTicketCount();
		LottoTickets autoLottoTickets = LottoTicketGenerator.generateRandomLottoTickets(countOfAutoLottoTickets);
		LottoTickets totalLottoTickets = manualLottoTickets.plus(autoLottoTickets);

		LottoView.displayPaidTicketCount(countOfManualTickets, countOfAutoLottoTickets);
		LottoView.displayLottoTickets(totalLottoTickets);
		return totalLottoTickets;
	}

	private static LottoTickets createManualTickets(int countOfManualTickets) {
		return new LottoTickets(
			LottoView.getManualLottoTickets(countOfManualTickets)
				.stream()
				.map(LottoTicket::of)
				.collect(Collectors.toList())
		);
	}

	private static WinningLottoTicket inputLottoWinningTicket() {
		LottoTicket winningTicket = LottoTicket.of(LottoView.getWinningNumbers());
		LottoNumber bonusNumber = LottoView.getBonusNumber();
		return new WinningLottoTicket(winningTicket, bonusNumber);
	}

	private static LottoGameResult runLottoGame(LottoTickets lottoTickets, WinningLottoTicket lottoWinningNumbers) {
		return lottoWinningNumbers.calculateWinningRanksOfTickets(lottoTickets);
	}

	private static void displayLottoGameResult(LottoGameResult lottoGameResult) {
		LottoView.displayResult(lottoGameResult);
		LottoView.displayYield(lottoGameResult.calculateYield());
	}
}
