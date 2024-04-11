package model.vo;

public class PurchaseAmount {
    private final int autoAmount;
    private final int manualAmount;

    public PurchaseAmount(int autoAmount, int manualAmount) {
        this.autoAmount = autoAmount;
        this.manualAmount = manualAmount;
    }

    public int getTotalAmount() {
        return autoAmount + manualAmount;
    }

    public int getManualAmount() {
        return manualAmount;
    }

    public int getAutoAmount() {
        return autoAmount;
    }
}
