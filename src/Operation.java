public enum Operation {
    ADDITION("+"),
    SUBTRACT("-"),
    MULTIPLICATION("*"),
    DIVISION("/");
    private final String value;

    Operation(String value) {
        this.value = value;
    }

    public static Operation of(String value) {
        for (var operation : Operation.values()) {
            if (operation.value.equals(value))
                return operation;
        }
        throw new IllegalArgumentException("Błąd - zły operator");
    }

    @Override
    public String toString() {
        return value;
    }
}
