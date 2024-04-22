package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import lotto.dto.LottoResultDto;
import lotto.domain.Prize;
import lotto.dto.TicketDto;

public class LottoView {

    private final Scanner scanner = new Scanner(System.in);

    public int getBudget() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        return parseInteger(input);
    }
    public void printOpeningMentionForPrevWeekWinNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

    }

    public void printOpeningMentionForManualNumber() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

    }

    public List<Integer> getNumbers() {
        String input = scanner.nextLine();
        return Arrays.stream(input.split(","))
            .map(this::parseInteger)
            .collect(Collectors.toList());
    }

    public int getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();
        return parseInteger(input);
    }

    public int getManualQuantity() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = scanner.nextLine();
        int manualQuantity = parseInteger(input);
        if (manualQuantity < 0) {
            throw new IllegalArgumentException("0이상의 숫자를 입력하세요");
        }
        return manualQuantity;
    }

    public void printTickets(List<TicketDto> tickets, int manualQuantity) {
        System.out.printf("수동으로 %d장, 자동으로 %d개 구매했습니다.\n", manualQuantity, tickets.size()-manualQuantity);
        tickets.forEach(ticketDto -> {
            List<Integer> sortedTickets = ticketDto.getNumbers().stream()
                .sorted()
                .collect(Collectors.toList());
            System.out.println(sortedTickets);
        });
    }

    public void printError(Exception exception) {
        System.out.println("[Error] "+ exception.getMessage());
    }

    public void printManualQuantityUnaffordableError() {
        System.out.println("수동으로 구매할 로또는 예산을 초과할 수 없습니다.");
    }

    private int parseInteger(String input) {
        try {
            int parsedInt = Integer.parseInt(input);
            return parsedInt;
        } catch (Exception e) {
            throw new RuntimeException("유효한 정수값을 입력해주세요.");
        }
    }

    public void printLottoResult(LottoResultDto result) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        result.getLottoResult().entrySet().stream()
            .filter((entry) -> entry.getKey() != Prize.NOTHING)
            .sorted((a, b) -> b.getKey().getOrder() - a.getKey().getOrder())
            .forEach((entry)-> System.out.println(generatePrizeString(entry.getKey()) + entry.getValue() + "개"));
        System.out.printf("총 수익률은 %.2f 입니다.", result.getResultRate());
        if (result.getResultRate() < 1.0d) {
            System.out.print("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
    }

    private String generatePrizeString(Prize prize) {
        if (prize == Prize.FIFTH) {
            return "3개 일치 (" + prize.getReward() + "원)- ";
        }
        if (prize == Prize.FOURTH) {
            return "4개 일치 (" + prize.getReward() + "원)- ";
        }
        if (prize == Prize.THIRD) {
            return "5개 일치 (" + prize.getReward() + "원)- ";
        }
        if (prize == Prize.SECOND) {
            return "5개 일치, 보너스 볼 일치(" + prize.getReward() + "원)- ";
        }
        if (prize == Prize.FIRST) {
            return "6개 일치 (" + prize.getReward() + "원)- ";
        }
        return "";
    }
}
