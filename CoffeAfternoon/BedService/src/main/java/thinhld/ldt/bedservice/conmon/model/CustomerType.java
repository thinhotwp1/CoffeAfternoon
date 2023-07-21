package thinhld.ldt.bedservice.conmon.model;

public enum CustomerType {
    MONTHLY_GUEST(0),
    DAY_GUEST(1);

    private final int value;

    private CustomerType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
