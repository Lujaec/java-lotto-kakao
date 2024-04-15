package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoStore {

    private static final int LOTTO_PRICE = 1000;

    private final int lottoCount;

    public LottoStore(int money) {
        this.lottoCount = money / LOTTO_PRICE;
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public void validateManualCount(int manualCount) {
        if (manualCount > lottoCount) {
            throw new IllegalArgumentException("금액으로 구입할 수 있는 로또의 매수를 초과하였습니다.");
        }
        if (manualCount < 0) {
            throw new IllegalArgumentException("양수를 입력해주세요.");
        }
    }

    private LottoTickets buyRandomLottoTickets(NumberGenerator numberGenerator, int manulCount) {
        return new LottoTickets(IntStream.range(0, lottoCount - manulCount)
                .mapToObj(number -> new LottoTicket(numberGenerator.generateNumbers()))
                .collect(Collectors.toList()));
    }

    private LottoTickets buyManualLottoTickets(List<String> userInput) {
        LottoTickets manualLottos = new LottoTickets();

        userInput.stream().forEach(manualLotto ->
                manualLottos.addTicket(new LottoTicket(manualLotto)));

        return manualLottos;
    }

    public LottoTickets buyLottoTickets(NumberGenerator numberGenerator, List<String> userInput) {
        LottoTickets lottoTickets = buyManualLottoTickets(userInput);

        return lottoTickets.joinTickets(buyRandomLottoTickets(numberGenerator, userInput.size()));
    }
}
