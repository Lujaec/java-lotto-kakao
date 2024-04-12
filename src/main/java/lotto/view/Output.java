package lotto.view;

import lotto.model.LottoResult;
import lotto.model.LottoTicket;
import lotto.model.LottoTickets;

import java.util.stream.Collectors;

public class Output {

    private Output() {
    }

    public static void printPurchaseAmount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printBoughtLottoTicket(LottoTickets selfTickets, LottoTickets autoTickets) {
        System.out.println("수동으로 " + selfTickets.getSize() + "장, 자동으로 " + autoTickets.getSize() + "개를 구매했습니다.");
        printLottoTickets(selfTickets);
        printLottoTickets(autoTickets);
        System.out.println();
    }

    public static void printResult(LottoResult result) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        result.produceStatics().entrySet().stream()
                .sorted((o1, o2) -> o1.getKey().getPrize() - o2.getKey().getPrize())
                .forEach(entry -> {
                    System.out.println(entry.getKey().getMatchCount() + "개 일치" + (entry.getKey().isMatchBonus() ? ", 보너스 볼 일치" : "")
                            + "(" + entry.getKey().getPrize() + ")- " + entry.getValue() + "개");
                });
        System.out.println("총 수익률은 " + result.calculateReturnRate() + "입니다.");

    }

    private static void printLottoTicket(LottoTicket ticket) {
        System.out.println("[" + ticket.getLottoNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "))
                + "]");
    }

    private static void printLottoTickets(LottoTickets tickets) {
        tickets.getLottoTickets().forEach(Output::printLottoTicket);
    }

    public static void printSelfLottoTickets() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }
}
