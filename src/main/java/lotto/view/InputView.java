package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int promptExpense() {
        System.out.println("구입금액을 입력해주세요.");
        return scanner.nextInt();
    }

    public int promptManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int result = scanner.nextInt();
        if (result < 0) {
            throw new IllegalArgumentException("0 또는 양수를 입력해야 합니다.");
        }
        return result;
    }

    public List<List<Integer>> promptManualNumbers(int count) {
        if (count == 0) {
            return Collections.emptyList();
        }

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(nextListInteger());
        }
        return result;
    }

    private List<Integer> nextListInteger() {
        return Arrays.stream(nextNotBlankLine().split(","))
                     .map(String::trim)
                     .map(Integer::parseInt)
                     .collect(Collectors.toList());
    }

    private String nextNotBlankLine() {
        String line = scanner.nextLine();
        if (line.isBlank()) {
            return nextNotBlankLine();
        }
        return line;
    }

    public List<Integer> promptWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
        return nextListInteger();
    }

    public int promptBonusNumber() {
        System.out.println("보너스 볼을 입력해주세요.");
        return scanner.nextInt();
    }
}

