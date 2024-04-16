package model.winningLottery;

import java.util.Objects;

import model.Ball;

public class WinningBonusNumber {
    private final Ball bonusNumber;

    public WinningBonusNumber(Ball bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public Ball getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningBonusNumber that = (WinningBonusNumber) o;
        return Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusNumber);
    }
}
