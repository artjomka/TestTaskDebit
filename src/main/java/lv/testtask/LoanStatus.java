package lv.testtask;

public enum LoanStatus {
    ONGOING(0), PAYED(1), DEBT_COLLECTOR(2);

    private Integer value;

    private LoanStatus(Integer value) {
        this.value = value;
    }

    public static LoanStatus getStatusByValue(Integer value) {
        switch (value) {
            case 0:
                return ONGOING;
            case 1:
                return PAYED;
            case 2:
                return DEBT_COLLECTOR;
            default:
                throw new IllegalArgumentException("No loan status foud for value " + value);
        }
    }

    public Integer getValue() {
        return value;
    }
}
