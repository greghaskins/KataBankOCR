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

}
