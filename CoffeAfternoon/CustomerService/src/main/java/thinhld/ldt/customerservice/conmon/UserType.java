package thinhld.ldt.customerservice.conmon;

public enum UserType {
    USER_ADMIN(0),
    USER_STAFF(1);

    private final int value;

    private UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
