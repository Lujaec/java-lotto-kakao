package com.lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.lotto.model.LottoGame;
import com.lotto.model.LottoTicket;
import com.lotto.model.LottoTickets;
import com.lotto.model.TargetLotto;

public class LottoGameInputView {
	private final Scanner SCANNER = new Scanner(System.in);

	public int inputMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return Integer.parseInt(SCANNER.nextLine());
	}

	public TargetLotto inputTargetNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		List<Integer> numbers = Arrays.stream(SCANNER.nextLine().split(","))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList());

		System.out.println("보너스 볼을 입력해 주세요.");
		int bonusNumber = Integer.parseInt(SCANNER.nextLine());
		return new TargetLotto(numbers, bonusNumber);
	}

	public int inputManualCount(int money) {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		int manualCount = Integer.parseInt(SCANNER.nextLine());
		if (money < manualCount * LottoGame.LOTTO_PRICE) {
			throw new IllegalArgumentException("수동으로 구매할 로또 수가 보유한 금액보다 많습니다.");
		}
		return manualCount;
	}

	public LottoTickets inputManualLottoTickets(int manualCount) {
		LottoTickets lottoTickets = new LottoTickets();
		if (manualCount == 0) {
			return lottoTickets;
		}

		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
		for (int i = 0; i < manualCount; i++) {
			lottoTickets.add(new LottoTicket(inputLottoNumbers()));
		}
		return lottoTickets;
	}

	private List<Integer> inputLottoNumbers() {
		return Arrays.stream(SCANNER.nextLine().split(","))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}
}
