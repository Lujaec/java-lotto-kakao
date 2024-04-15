package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WinningLotto {

    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        this(new LottoTicket(winningNumbers), bonusNumber);
    }

    public WinningLotto(LottoTicket winningTicket, LottoNumber bonusNumber) {
        validateWinningNumber(winningTicket, bonusNumber);
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    public Prize calculatePrize(LottoTicket ticket) {
        int matchCount = winningTicket.countMatchedNumbers(ticket);
        boolean isBonusNumberMatched = ticket.contains(bonusNumber);
        return Prize.evaluate(matchCount, isBonusNumberMatched);
    }

    public LottoResult aggregateResult(LottoTickets tickets) {
        Map<Prize, Long> result = tickets.stream()
                .map(this::calculatePrize)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return new LottoResult(result);
    }

    private void validateWinningNumber(LottoTicket winningTicket, LottoNumber bonusLottoNumber) {
        if (winningTicket.contains(bonusLottoNumber)) {
            throw new IllegalArgumentException("보너스볼은 당첨 번호와 중복되지 않아야 합니다.");
        }
    }
}
