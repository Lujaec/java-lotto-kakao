package lotto.model.vo;

import java.util.Objects;

public class PurchaseCount {

    private static final int ZERO = 0;
    private static final String INVALID_COUNT_MESSAGE = "구입 개수는 1개 이상이어야 합니다.";
    private static final String INVALID_MANUAL_LOTTO_COUNT_MESSAGE = "수동 구매 개수는 구입 개수보다 작거나 같아야 합니다.";

    private final int count;

    public PurchaseCount(final int count) {
        validateCount(count);
        this.count = count;
    }

    private void validateCount(final int count) {
        if (count <= ZERO) {
            throw new IllegalArgumentException(INVALID_COUNT_MESSAGE);
        }
    }

    public int calculateAutoLottoCount(final ManualLottoCount manualLottoCount) {
        validateManualPurchaseCount(manualLottoCount);
        return this.count - manualLottoCount.getCount();
    }

    private void validateManualPurchaseCount(final ManualLottoCount manualLottoCount) {
        if (count < manualLottoCount.getCount()) {
            throw new IllegalArgumentException(INVALID_MANUAL_LOTTO_COUNT_MESSAGE);
        }
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PurchaseCount that = (PurchaseCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
