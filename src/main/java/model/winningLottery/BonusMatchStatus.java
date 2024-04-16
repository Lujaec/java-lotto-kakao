package model.winningLottery;

public enum BonusMatchStatus {
    IS_MATCH {
        @Override
        public boolean matchBonusNumber(boolean isBonusNumberMatch) {
            return isBonusNumberMatch;
        }
    },
    IS_NOT_MATCH {
        @Override
        public boolean matchBonusNumber(boolean isBonusNumberMatch) {
            return !isBonusNumberMatch;
        }
    },
    IS_UNRELATED {
        @Override
        public boolean matchBonusNumber(boolean isBonusNumberMatch) {
            return true;
        }
    },
    ;

    public abstract boolean matchBonusNumber(boolean isBonusNumberMatch);
}
