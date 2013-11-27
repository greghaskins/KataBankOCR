package com.greghaskins.bankocr.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class AccountNumberTest {

    private ChecksumCalculator mockCalculator;

    @Before
    public void setUp() throws Exception {
        this.mockCalculator = mock(ChecksumCalculator.class);
    }

    @Test
    public void accountNumbersHaveNineDigits() throws Exception {
        assertThat(AccountNumber.NUMBER_OF_DIGITS, equalTo(9));
    }

    @Test
    public void canGetDigits() throws Exception {
        final Digit[] digits = getRandomDigits();
        final AccountNumber accountNumber = new AccountNumber(digits);
        assertThat(accountNumber.getDigits(), equalTo(digits));
    }

    @Test
    public void displayValueComesFromDigits() throws Exception {
        final AccountNumber accountNumber = new AccountNumber(Digit.THREE, Digit.FOUR, Digit.FIVE,
                Digit.EIGHT, Digit.EIGHT, Digit.TWO, Digit.EIGHT, Digit.SIX, Digit.FIVE);

        when(this.mockCalculator.computeChecksum(accountNumber)).thenReturn(121);
        assertThat(accountNumber.getDisplayValue(this.mockCalculator), equalTo("345882865"));
    }

    @Test
    public void displayValueShowsMarkerIfAnyDigitsAreIllegible() throws Exception {
        final AccountNumber accountNumber = new AccountNumber(Digit.EIGHT, Digit.TWO, Digit.EIGHT,
                Digit.NINE, Digit.SEVEN, Digit.ONE, Digit.UNKNOWN, Digit.THREE, Digit.SIX);

        assertThat(accountNumber.getDisplayValue(this.mockCalculator), equalTo("828971?36 ILL"));
    }

    @Test
    public void displayValueShowsMarkerIfChecksumDoesntVerifyMod11() throws Exception {
        final AccountNumber accountNumber = new AccountNumber(Digit.SIX, Digit.SIX, Digit.FOUR,
                Digit.THREE, Digit.SEVEN, Digit.ONE, Digit.FOUR, Digit.NINE, Digit.FIVE);

        when(this.mockCalculator.computeChecksum(accountNumber)).thenReturn(12);
        assertThat(accountNumber.getDisplayValue(this.mockCalculator), equalTo("664371495 ERR"));
        reset(this.mockCalculator);

        when(this.mockCalculator.computeChecksum(accountNumber)).thenReturn(113);
        assertThat(accountNumber.getDisplayValue(this.mockCalculator), equalTo("664371495 ERR"));
        reset(this.mockCalculator);

        when(this.mockCalculator.computeChecksum(accountNumber)).thenReturn(-1);
        assertThat(accountNumber.getDisplayValue(this.mockCalculator), equalTo("664371495 ERR"));
        reset(this.mockCalculator);

        when(this.mockCalculator.computeChecksum(accountNumber)).thenReturn(22);
        assertThat(accountNumber.getDisplayValue(this.mockCalculator), equalTo("664371495"));
    }

    public static AccountNumber makeAccountNumber(final Integer... integers) {
        final Digit[] digits = new Digit[integers.length];
        for (int index = 0; index < integers.length; index++) {
            digits[index] = getDigit(integers[index]);
        }
        return new AccountNumber(digits);
    }

    private static Digit getDigit(final Integer integerValue) {
        for (final Digit digit : Digit.values()) {
            if (integerValue == null && digit.getIntegerValue() == null) {
                return digit;
            } else if (integerValue != null && integerValue.equals(digit.getIntegerValue())) {
                return digit;
            }
        }
        return null;
    }

    private static Digit[] getRandomDigits() {
        final Random random = new Random();

        final Digit[] randomDigits = new Digit[AccountNumber.NUMBER_OF_DIGITS];
        final Digit[] allValues = Digit.values();

        for (int i = 0; i < randomDigits.length; i++) {
            randomDigits[i] = allValues[random.nextInt(allValues.length)];
        }
        return randomDigits;
    }
}
