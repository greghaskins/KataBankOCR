package com.greghaskins.bankocr.model;

public class ChecksumCalculator {

    public int computeChecksum(final AccountNumber accountNumber) {
        final int numberOfDigits = AccountNumber.NUMBER_OF_DIGITS;
        final Digit[] digits = accountNumber.getDigits();

        if (digits == null || digits.length != numberOfDigits) {
            return -1;
        }

        int checksum = 0;
        for (int index = 0; index < numberOfDigits; index++) {
            final Integer integerValue = digits[index].getIntegerValue();
            if (integerValue == null) {
                return -1;
            }
            checksum += integerValue.intValue() * (numberOfDigits - index);
        }

        return checksum;
    }

}
