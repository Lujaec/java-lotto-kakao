package controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.lotto.Lotto;
import domain.LottoGenerator;
import domain.LottoMoney;
import domain.lotto.LottoNumber;
import domain.lotto.Lottos;
import domain.lotto.WinningLotto;
import domain.WinningResult;
import view.LottoGameView;
import view.dto.WinningResultDto;

public class LottoGameController {
	private final LottoGameView lottoGameView;
	private final LottoGenerator lottoGenerator;

	public LottoGameController(LottoGameView lottoGameView, LottoGenerator lottoGenerator) {
		this.lottoGameView = lottoGameView;
		this.lottoGenerator = lottoGenerator;
	}

	public void play() {
		LottoMoney lottoMoney = getLottoMoney();

		Lottos bought = buyLottos(lottoMoney);
		lottoGameView.printPurchasedLottos(bought);

		Lotto winningLottoNumbers = getWinningLottoNumbers();
		WinningLotto winningLotto = getWinningLotto(winningLottoNumbers);
		WinningResult winningResult = bought.calculateWinningResult(winningLotto);

		lottoGameView.printWinningRank(WinningResultDto.fromWinningResult(winningResult));
		lottoGameView.printEarningRate(winningResult.calculateEarningRate(lottoMoney));
	}

	private LottoMoney getLottoMoney() {
		try {
			return new LottoMoney(lottoGameView.getLottoMoneyInput());
		} catch (IllegalArgumentException e) {
			System.out.println("[ERROR] " + e.getMessage());
			return getLottoMoney();
		}
	}

	private Lottos buyLottos(LottoMoney lottoMoney) {
		int manualLottoCount = lottoGameView.getManualLottoCount();
		Lottos manualLottos = getManualLottos(manualLottoCount);

		LottoMoney remain = lottoMoney.calculateRemainAfterBuy(manualLottoCount);
		int autoLottoCount = remain.calculateLottoCount();
		Lottos autoLottos = lottoGenerator.generateLottos(autoLottoCount);

		lottoGameView.printPurchasedLottoCount(manualLottoCount, autoLottoCount);
		return manualLottos.concat(autoLottos);
	}

	private Lottos getManualLottos(int count) {
		return new Lottos(Stream.generate(lottoGameView::getManualLottoNumbers)
			.limit(count)
			.map(Lotto::new)
			.collect(Collectors.toList()));
	}

	private Lotto getWinningLottoNumbers() {
		try {
			List<LottoNumber> winningLottos = lottoGameView.getWinningLottoNumbers();
			return new Lotto(winningLottos);
		} catch (IllegalArgumentException e) {
			System.out.println("[ERROR] " + e.getMessage());
			return getWinningLottoNumbers();
		}
	}

	private WinningLotto getWinningLotto(Lotto winningLottoNumbers) {
		try {
			LottoNumber bonusNumber = new LottoNumber(lottoGameView.getBonusNumber());
			return new WinningLotto(winningLottoNumbers, bonusNumber);
		} catch (IllegalArgumentException e) {
			System.out.println("[ERROR] " + e.getMessage());
			return getWinningLotto(winningLottoNumbers);
		}
	}
}
