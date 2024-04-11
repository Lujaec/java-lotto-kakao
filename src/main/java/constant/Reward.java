package constant;

public enum Reward {
    FIRST(2_000_000_000, 6, false),
    SECOND(30_000_000, 5, true),
    THIRD(1_500_000, 5, false),
    FOURTH(50_000, 4, false),
    FIFTH(5_000, 3, false);

    private long prize;
    private int matchCount;
    private boolean bonus;

    Reward(int prize, int matchCount, boolean bonus) {
        this.prize = prize;
        this.matchCount = matchCount;
        this.bonus = bonus;
    }

    public long getPrice() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean getBonus() {
        return bonus;
    }
}
