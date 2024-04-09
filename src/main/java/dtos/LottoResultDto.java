package dtos;

import domains.WinningResult;

import java.util.*;
import java.util.stream.Collectors;

public class LottoResultDto {
    private final List<WinningResultDto> winningResultDtos;
    private final double revenueRate;

    public LottoResultDto(List<WinningResultDto> winningResultDtos, double revenueRate) {
        this.winningResultDtos = winningResultDtos;
        this.revenueRate = revenueRate;
    }

    public static LottoResultDto of(List<WinningResult> winningResults, double revenueRate) {
        winningResults.remove(WinningResult.NONE);
        List<WinningResultDto> winningResultDtos = Arrays.stream(WinningResult.values())
                .map(winningResult -> {
                    int count = (int) winningResults.stream().filter(winningResult::equals).count();
                    return WinningResultDto.of(winningResult, count);
                }).collect(Collectors.toList());
        winningResultDtos.removeIf(dto -> dto.getRegularBallMatch() == 0);
        return new LottoResultDto(winningResultDtos, revenueRate);
    }

    public List<WinningResultDto> getWinningResultDtos() {
        return Collections.unmodifiableList(winningResultDtos);
    }

    public double getRevenueRate() {
        return revenueRate;
    }
}
