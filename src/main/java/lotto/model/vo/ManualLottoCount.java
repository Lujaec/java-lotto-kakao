package lotto.model.vo;

import java.util.Objects;

public class ManualLottoCount {

    private static final int ZERO = 0;
    private static final String INVALID_COUNT_MESSAGE = "수동 구매 개수는 0개 이상이어야 합니다.";

    private final int count;

    public ManualLottoCount(final int count) {
        validateCount(count);
        this.count = count;
    }

    private void validateCount(final int count) {
        if (count < ZERO) {
            throw new IllegalArgumentException(INVALID_COUNT_MESSAGE);
        }
    }

    public boolean isZero() {
        return count == ZERO;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManualLottoCount that = (ManualLottoCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
