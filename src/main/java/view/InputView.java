package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import model.LottoNumbers;
import model.winningLottery.WinningBonusNumber;

public class InputView {
    static Scanner sc = new Scanner(System.in);

    public static int amountInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }

    public static int manualCountInput() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }

    public static String manualLottoInput() {
        return sc.nextLine();
    }

    public static List<LottoNumbers> manualLottoIn(int manualCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<LottoNumbers> lottoNumbers = new ArrayList<>();

        for (int i = 0; i < manualCount; i++) {
            String input = sc.nextLine();

            lottoNumbers.add(LottoNumbers.createLottoNumbers(input));
        }

        return lottoNumbers;
    }

    public static String winningNumbersInput() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return sc.nextLine();
    }

    public static int winningBonusNumberInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }
}
