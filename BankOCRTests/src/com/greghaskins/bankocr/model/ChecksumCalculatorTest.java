package com.greghaskins.bankocr.model;

import static com.greghaskins.bankocr.model.AccountNumberTest.makeAccountNumber;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

public class ChecksumCalculatorTest {
    private ChecksumCalculator calculator;

    @Before
    public void setUp() throws Exception {
        this.calculator = new ChecksumCalculator();
    }

    @Test
    public void calculatesChecksumByMultiplyingTheDigitIndicesByTheirValues_Case1()
            throws Exception {
        final AccountNumber accountNumber = makeAccountNumber(1, 1, 1, 1, 1, 1, 1, 1, 1);
        assertThat(this.calculator.computeChecksum(accountNumber), equalTo(45));
    }

    @Test
    public void calculatesChecksumByMultiplyingTheDigitIndicesByTheirValues_Case2()
            throws Exception {
        final AccountNumber accountNumber = makeAccountNumber(3, 4, 5, 8, 8, 2, 8, 6, 5);
        assertThat(this.calculator.computeChecksum(accountNumber), equalTo(231));
    }

    @Test
    public void calculatesChecksumByMultiplyingTheDigitIndicesByTheirValues_Case3()
            throws Exception {
        final AccountNumber accountNumber = makeAccountNumber(0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertThat(this.calculator.computeChecksum(accountNumber), equalTo(0));
    }

    @Test
    public void calculatesChecksumByMultiplyingTheDigitIndicesByTheirValues_Case4()
            throws Exception {
        final AccountNumber accountNumber = makeAccountNumber(1, 1, 0, 5, 1, 9, 5, 5, 3);
        assertThat(this.calculator.computeChecksum(accountNumber), equalTo(116));
    }

    @Test
    public void returnsNegativeOneIfFewerThanNineDigitsInAccountNumber() throws Exception {
        final AccountNumber accountNumberWith8Digits = makeAccountNumber(1, 2, 3, 4, 5, 6, 7, 8);
        assertThat(this.calculator.computeChecksum(accountNumberWith8Digits), equalTo(-1));
    }

    @Test
    public void returnsNegativeOneIfGreaterThanNineDigitsInAccountNumber() throws Exception {
        final AccountNumber accountNumberWith10Digits = makeAccountNumber(1, 2, 3, 4, 5, 6, 7, 8,
                9, 0);
        assertThat(this.calculator.computeChecksum(accountNumberWith10Digits), equalTo(-1));
    }

    @Test
    public void returnsNegativeOneIfDigitsAreNull() throws Exception {
        assertThat(this.calculator.computeChecksum(new AccountNumber((Digit[]) null)), equalTo(-1));
    }

    @Test
    public void returnsNegativeOneIfAnyDigitIsUnknown() throws Exception {
        final AccountNumber accountNumberWithUnknown = makeAccountNumber(1, 2, null, 3, 4, 5, 6, 7,
                8);
        assertThat(this.calculator.computeChecksum(accountNumberWithUnknown), equalTo(-1));
    }
}
