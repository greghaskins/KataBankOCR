package com.greghaskins.bankocr.model;

public enum Digit {
    UNKNOWN(null),

    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9);

    private Integer value;

    private Digit(final Integer value) {
        this.value = value;
    }

    private Digit(final int value) {
        this(new Integer(value));
    }

    public String getDisplayValue() {
        return this.value == null ? "?" : String.valueOf(this.value);
    }

    public Integer getIntegerValue() {
        return this.value;
    }
}
