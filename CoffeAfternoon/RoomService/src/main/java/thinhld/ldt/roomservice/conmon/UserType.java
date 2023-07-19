package thinhld.ldt.roomservice.conmon;

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
    // Return role string
    public static UserType fromValue(int value) {
        for (UserType userType : UserType.values()) {
            if (userType.getValue() == value) {
                return userType;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
