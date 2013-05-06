package com.greghaskins.bankocr.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.greghaskins.bankocr.model.AccountNumber;
import com.greghaskins.bankocr.model.Digit;
import com.greghaskins.bankocr.model.Glyph;
import com.greghaskins.bankocr.model.SevenSegmentGlyph;
import com.greghaskins.bankocr.model.TokenizedEntry;

public class AccountEntryParser {

    private static final Map<Glyph, Digit> validDigits = new HashMap<Glyph, Digit>();
    static {
        validDigits.put(SevenSegmentGlyph.ZERO, Digit.ZERO);
        validDigits.put(SevenSegmentGlyph.ONE, Digit.ONE);
        validDigits.put(SevenSegmentGlyph.TWO, Digit.TWO);
        validDigits.put(SevenSegmentGlyph.THREE, Digit.THREE);
        validDigits.put(SevenSegmentGlyph.FOUR, Digit.FOUR);
        validDigits.put(SevenSegmentGlyph.FIVE, Digit.FIVE);
        validDigits.put(SevenSegmentGlyph.SIX, Digit.SIX);
        validDigits.put(SevenSegmentGlyph.SEVEN, Digit.SEVEN);
        validDigits.put(SevenSegmentGlyph.EIGHT, Digit.EIGHT);
        validDigits.put(SevenSegmentGlyph.NINE, Digit.NINE);
    }

    public AccountNumber parseEntry(final TokenizedEntry tokenizedEntry) {
        final ArrayList<Digit> digits = new ArrayList<Digit>();
        for (final Glyph glyph : tokenizedEntry) {
            if (validDigits.containsKey(glyph)) {
                digits.add(validDigits.get(glyph));
            } else {
                digits.add(Digit.UNKNOWN);
            }
        }

        return new AccountNumber(digits.toArray(new Digit[0]));
    }

}
