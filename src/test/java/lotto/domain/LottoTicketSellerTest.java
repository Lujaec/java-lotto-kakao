package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class LottoTicketSellerTest {

    @Test
    public void 판매자는_구매_금액에_상응하는_개수의_로또_번호를_만들어준다() {
        LottoPurchaseBudget budget = new LottoPurchaseBudget(15000);

        LottoTickets generatedTickets = LottoTicketSeller.purchaseAutoLottoTickets(budget);
        Assertions.assertThat(generatedTickets.size()).isEqualTo(15);
    }


}
