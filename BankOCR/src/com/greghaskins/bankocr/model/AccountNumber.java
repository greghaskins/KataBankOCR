package com.greghaskins.bankocr.model;

public class AccountNumber {

    public static final int NUMBER_OF_DIGITS = 9;
    private final Digit[] digits;

    public AccountNumber(final Digit... digits) {
        this.digits = digits;

    }

    public Digit[] getDigits() {
        return this.digits;
    }

    public String getDisplayValue() {
        final StringBuilder builder = new StringBuilder();
        for (final Digit digit : this.digits) {
            builder.append(digit.getDisplayValue());
        }
        return builder.toString();
    }

}
