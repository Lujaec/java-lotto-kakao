package domain;

import java.util.List;
import java.util.Objects;

public class LottoStore {

    private static final Money LOTTO_PRICE = new Money(1000);

    private Money availableMoney;
    private ManualLotto manualLottos;
    private AutoLotto autoLottos;

    public LottoStore(Money money) {
        this.availableMoney = money;
    }

    public int getManualLottoCount() {
        return Objects.nonNull(manualLottos) ? manualLottos.getLottoSize() : 0;
    }

    public int getAutoLottoCount() {
        return Objects.nonNull(autoLottos)? autoLottos.getLottoSize() : 0;
    }

    public void buyAutoLottos(NumberGenerator numberGenerator) {
        Quantity availableAutoLottoCount = availableMoney.calculatePurchaseQuantity(LOTTO_PRICE);
        this.autoLottos = new AutoLotto(availableAutoLottoCount.getValue(), numberGenerator);
    }

    public void buyManualLottos(List<String> manualLottoNumbers) {
        this.manualLottos = new ManualLotto(manualLottoNumbers);
        this.availableMoney = availableMoney.buy(LOTTO_PRICE, new Quantity(manualLottos.getLottoSize()));
    }

    public List<LottoTicket> getLottos() {
        List<LottoTicket> lottos = manualLottos.getLottos();
        lottos.addAll(autoLottos.getLottos());
        return lottos;
    }
}
