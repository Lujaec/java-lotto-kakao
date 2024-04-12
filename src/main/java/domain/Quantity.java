package domain;

import java.util.Objects;

public class Quantity {

    private final long value;

    public static Quantity subtract(long dividnd, long divisor) {
        return new Quantity(dividnd / divisor);
    }

    public Quantity(long value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("수량은 0이상이어야 합니다.");
        }
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Quantity quantity = (Quantity) o;
        return value == quantity.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
