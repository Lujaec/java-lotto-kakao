package view.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.Rank;
import domain.WinningResult;

public class WinningResultDto {
    private final int requiredMatchCount;
    private final long winningMoney;
    private final int matchCount;
    private final boolean requiresBonusNumber;
    
    private WinningResultDto(int requiredMatchCount, long winningMoney, int matchCount, boolean requiresBonusNumber) {
        this.requiredMatchCount = requiredMatchCount;
        this.winningMoney = winningMoney;
        this.matchCount = matchCount;
        this.requiresBonusNumber = requiresBonusNumber;
    }
    
    private static WinningResultDto of(Rank rank, int matchCount) {
        return new WinningResultDto(
            rank.getMatchNumberCount(),
            rank.getWinningMoney().getMoney(),
            matchCount,
            rank == Rank.SECOND_WIN
        );
    }
    
    public static List<WinningResultDto> fromWinningResult(WinningResult winningResult) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank != Rank.NONE)
            .map(rank -> WinningResultDto.of(rank, winningResult.getWinningCount(rank)))
            .collect(Collectors.toList());
    }
    
    public int getRequiredMatchCount() {
        return requiredMatchCount;
    }
    
    public long getWinningMoney() {
        return winningMoney;
    }
    
    public int getMatchCount() {
        return matchCount;
    }
    
    public boolean requiresBonusNumber() {
        return requiresBonusNumber;
    }
}
