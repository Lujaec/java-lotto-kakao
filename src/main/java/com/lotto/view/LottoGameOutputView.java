package com.lotto.view;

import com.lotto.model.LottoPlayer;
import com.lotto.model.LottoRank;
import com.lotto.model.LottoResult;
import com.lotto.model.LottoResults;

import java.util.Collections;
import java.util.List;

public class LottoGameOutputView {

    public void printLottoTicketSize(int manualLottoTicketSize, int autoLottoTicketSize) {
        StringBuilder sb = new StringBuilder();
        sb.append("수동으로 ")
                .append(manualLottoTicketSize)
                .append("장, ")
                .append("자동으로 ")
                .append(autoLottoTicketSize)
                .append("개를 구매했습니다.");
        System.out.println(sb);
    }

    public void printLottoTicketList(LottoPlayer lottoPlayer) {
        List<List<Integer>> lottoTicketList = ViewMapper.getLottoTicketList(lottoPlayer);
        lottoTicketList.forEach(System.out::println);
    }


    public void printLottoResults(LottoResults lottoResults) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        List<LottoResult> reversedLottoResults = getReversedLottoResults(lottoResults);
        reversedLottoResults.stream()
                .filter(result -> !result.isFailed())
                .forEach(this::printLottoResult);
    }

    public void printProfitRate(LottoResults lottoResults) {
        System.out.println("총 수익률은 " + String.format("%.02f", lottoResults.calculateProfit()) + "입니다.");
    }

    private void printLottoResult(LottoResult lottoResult) {
        LottoRank lottoRank = lottoResult.getLottoRank();
        String lottoRankString = lottoRankToString(lottoRank);
        System.out.println(lottoRankString + lottoResult.getCount() + "개");
    }

    private String lottoRankToString(LottoRank lottoRank) {
        int matchCount = lottoRank.getMatchCount();
        boolean bonus = lottoRank.isBonus();
        int prize = lottoRank.getPrize();

        String prefix = matchCount + "개 일치";
        if (bonus) {
            prefix += ", 보너스 볼 일치";
        }

        return (prefix + " (" + prize + "원)- ");
    }

    private List<LottoResult> getReversedLottoResults(LottoResults lottoResults) {
        List<LottoResult> lottoResult = lottoResults.getLottoResults();
        Collections.reverse(lottoResult);
        return lottoResult;
    }
}
