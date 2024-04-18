package com.lotto.view;

import com.lotto.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoGameInputView {
    private final Scanner scanner = new Scanner(System.in);

    public int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public List<Integer> inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return StringUtils.parseCommaSeparatedToIntegerList(scanner.nextLine());
    }

    public int inputManualLottoTicketSize() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public List<List<Integer>> inputManualLottoTicketNumbers(int manualLottoTicketSize) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualLottoTicketNumbers = new ArrayList<>();
        for (int i = 0; i < manualLottoTicketSize; i++) {
            List<Integer> manualLottoNumbers = StringUtils.parseCommaSeparatedToIntegerList(scanner.nextLine());
            manualLottoTicketNumbers.add(manualLottoNumbers);
        }
        return manualLottoTicketNumbers;
    }
}
