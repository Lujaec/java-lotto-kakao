package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    static Scanner sc = new Scanner(System.in);

    public static int budgetInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }

    public static int manualAmountInput() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }

    public static List<String> manualNumbersInput(int numberOfLines) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");

        List<String> manualNumbers = new ArrayList<>();
        for (int i = 0; i < numberOfLines; i++) {
            manualNumbers.add(sc.nextLine());
        }
        return manualNumbers;
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
