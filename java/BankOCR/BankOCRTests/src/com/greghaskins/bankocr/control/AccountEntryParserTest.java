package com.greghaskins.bankocr.control;

import static com.greghaskins.bankocr.model.AccountNumberTest.makeAccountNumber;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Test;

import com.greghaskins.bankocr.model.AccountNumber;
import com.greghaskins.bankocr.model.Digit;
import com.greghaskins.bankocr.model.Glyph;
import com.greghaskins.bankocr.model.SevenSegmentGlyph;
import com.greghaskins.bankocr.model.TokenizedEntry;

public class AccountEntryParserTest {

    @Test
    public void testNumericDigitsAreRecognized() throws Exception {
        final TokenizedEntry tokenizedEntry = new TokenizedEntry();
        tokenizedEntry.add(SevenSegmentGlyph.EIGHT);
        tokenizedEntry.add(SevenSegmentGlyph.SIX);
        tokenizedEntry.add(SevenSegmentGlyph.SEVEN);
        tokenizedEntry.add(SevenSegmentGlyph.FIVE);
        tokenizedEntry.add(SevenSegmentGlyph.THREE);
        tokenizedEntry.add(SevenSegmentGlyph.ZERO);
        tokenizedEntry.add(SevenSegmentGlyph.NINE);

        final AccountNumber expectedAccountNumber = makeAccountNumber(8, 6, 7, 5, 3, 0, 9);

        final AccountEntryParser parser = new AccountEntryParser();
        assertThat(parser.parseEntry(tokenizedEntry), equalTo(expectedAccountNumber));
    }

    @Test
    public void unknownDigitsAreMarkedAppropriately() throws Exception {
        final TokenizedEntry tokenizedEntry = new TokenizedEntry();
        tokenizedEntry.add(SevenSegmentGlyph.TWO);
        tokenizedEntry.add(SevenSegmentGlyph.ONE);
        tokenizedEntry.add(SevenSegmentGlyph.SEVEN);
        tokenizedEntry.add(mock(Glyph.class));
        tokenizedEntry.add(SevenSegmentGlyph.THREE);
        tokenizedEntry.add(mock(Glyph.class));
        tokenizedEntry.add(SevenSegmentGlyph.FOUR);
        tokenizedEntry.add(SevenSegmentGlyph.EIGHT);
        tokenizedEntry.add(SevenSegmentGlyph.ONE);

        final AccountNumber expectedAccountNumber = makeAccountNumber(2, 1, 7, null, 3, null, 4, 8,
                1);

        final AccountEntryParser parser = new AccountEntryParser();
        assertThat(parser.parseEntry(tokenizedEntry), equalTo(expectedAccountNumber));

    }

    private static Matcher<AccountNumber> equalTo(final AccountNumber accountNumber) {
        return new IsEqualAccountNumber(accountNumber);
    }

    private static class IsEqualAccountNumber extends TypeSafeDiagnosingMatcher<AccountNumber> {

        private final AccountNumber accountNumber;

        public IsEqualAccountNumber(final AccountNumber accountNumber) {
            this.accountNumber = accountNumber;
        }

        @Override
        public void describeTo(final Description description) {
            final AccountNumber number = this.accountNumber;
            describeAccountNumber(number, description);

        }

        private static void describeAccountNumber(final AccountNumber number,
                final Description description) {

            description.appendText("<AccountNumber ");
            for (final Digit digit : number.getDigits()) {
                description.appendText(digit.getDisplayValue());
            }
            description.appendText(">");
        }

        @Override
        protected boolean matchesSafely(final AccountNumber otherAccountNumber,
                final Description description) {
            describeAccountNumber(otherAccountNumber, description);
            final Matcher<Digit[]> digitMatcher = Matchers.equalTo(this.accountNumber.getDigits());
            return digitMatcher.matches(otherAccountNumber.getDigits());
        }

    }

}
