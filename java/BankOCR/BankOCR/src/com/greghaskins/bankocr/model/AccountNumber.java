package com.greghaskins.bankocr.model;

import java.util.Arrays;

public class AccountNumber {

    public static final int NUMBER_OF_DIGITS = 9;
    private final Digit[] digits;

    public AccountNumber(final Digit... digits) {
        this.digits = digits;

    }

    public Digit[] getDigits() {
        return this.digits;
    }

    public String getDisplayValue(final ChecksumCalculator checksumCalculator) {
        final StringBuilder builder = new StringBuilder();
        for (final Digit digit : this.digits) {
            builder.append(digit.getDisplayValue());
        }

        return builder.toString() + this.getDisplaySuffix(checksumCalculator);
    }

    private String getDisplaySuffix(final ChecksumCalculator calculator) {
        if (Arrays.asList(this.digits).contains(Digit.UNKNOWN)) {
            return " ILL";
        } else if (calculator.computeChecksum(this) % 11 != 0) {
            return " ERR";
        } else {
            return "";
        }
    }

}
