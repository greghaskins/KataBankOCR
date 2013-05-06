package com.greghaskins.bankocr.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class DigitTest extends EnumTestCase<Digit> {
    private static final String[] EXPECTED_VALUES = new String[] { "UNKNOWN", "ZERO", "ONE", "TWO",
            "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", };

    @Override
    protected String[] getExpectedValues() {
        return EXPECTED_VALUES;
    }

    @Override
    protected Class<Digit> getEnumType() {
        return Digit.class;
    }

    @Test
    public void displayValues() throws Exception {
        assertThat(Digit.UNKNOWN.getDisplayValue(), equalTo("?"));
        assertThat(Digit.ONE.getDisplayValue(), equalTo("1"));
        assertThat(Digit.TWO.getDisplayValue(), equalTo("2"));
        assertThat(Digit.THREE.getDisplayValue(), equalTo("3"));
        assertThat(Digit.FOUR.getDisplayValue(), equalTo("4"));
        assertThat(Digit.FIVE.getDisplayValue(), equalTo("5"));
        assertThat(Digit.SIX.getDisplayValue(), equalTo("6"));
        assertThat(Digit.SEVEN.getDisplayValue(), equalTo("7"));
        assertThat(Digit.EIGHT.getDisplayValue(), equalTo("8"));
        assertThat(Digit.NINE.getDisplayValue(), equalTo("9"));
    }

    @Test
    public void integerValues() throws Exception {
        assertThat(Digit.UNKNOWN.getIntegerValue(), equalTo(null));
        assertThat(Digit.ONE.getIntegerValue(), equalTo(new Integer(1)));
        assertThat(Digit.TWO.getIntegerValue(), equalTo(new Integer(2)));
        assertThat(Digit.THREE.getIntegerValue(), equalTo(new Integer(3)));
        assertThat(Digit.FOUR.getIntegerValue(), equalTo(new Integer(4)));
        assertThat(Digit.FIVE.getIntegerValue(), equalTo(new Integer(5)));
        assertThat(Digit.SIX.getIntegerValue(), equalTo(new Integer(6)));
        assertThat(Digit.SEVEN.getIntegerValue(), equalTo(new Integer(7)));
        assertThat(Digit.EIGHT.getIntegerValue(), equalTo(new Integer(8)));
        assertThat(Digit.NINE.getIntegerValue(), equalTo(new Integer(9)));
    }

}
