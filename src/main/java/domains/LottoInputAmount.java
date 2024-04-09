package domains;

public class LottoInputAmount {
    public static final int LOTTO_PRICE = 1000;
    private final int money;
    private final int numberOfManualLotto;

    public LottoInputAmount(int money, int numberOfManualLotto) {
        validateMoneyRange(money);
        validateManualLottoInput(money, numberOfManualLotto);

        this.money = money;
        this.numberOfManualLotto = numberOfManualLotto;
    }

    private static void validateManualLottoInput(int money, int numberOfManualLotto) {
        if (numberOfManualLotto < 0) {
            throw new RuntimeException("수동 구매 개수는 0 이상이어야합니다.");
        }
        if (money - numberOfManualLotto * LOTTO_PRICE < 0) {
            throw new RuntimeException("입력된 금액으로 수동 로또를 모두 구매할 수 없습니다.");
        }
    }

    public int getNumberOfLottos() {
        return money / LOTTO_PRICE;
    }

    public int getNumberOfAutoLottos() {
        return getNumberOfLottos() - numberOfManualLotto;
    }

    private void validateMoneyRange(int money) {
        if (money < LOTTO_PRICE) {
            throw new RuntimeException("로또 구매 금액은 1000 이상이어야합니다.");
        }
    }
}
