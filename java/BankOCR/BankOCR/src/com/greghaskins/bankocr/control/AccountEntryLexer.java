package com.greghaskins.bankocr.control;

import com.greghaskins.bankocr.model.TokenizedEntry;


public class AccountEntryLexer {

    private static final int WIDTH_OF_GLYPH = 3;
    private final GlyphScanner glyphScanner;

    public AccountEntryLexer(final GlyphScanner glyphScanner) {
        this.glyphScanner = glyphScanner;
    }

    public TokenizedEntry tokenizeEntry(final String topLine, final String middleLine,
            final String bottomLine) {

        final int lengthOfLongestLine = getLengthOfLongestLine(topLine, middleLine, bottomLine);

        final String topLinePaddedWithSpaces = padRight(topLine, lengthOfLongestLine);
        final String middleLinePaddedWithSpaces = padRight(middleLine, lengthOfLongestLine);
        final String bottomLinePaddedWithSpaces = padRight(bottomLine, lengthOfLongestLine);

        return this.scanLines(topLinePaddedWithSpaces, middleLinePaddedWithSpaces,
                bottomLinePaddedWithSpaces);

    }

    private TokenizedEntry scanLines(final String topLine, final String middleLine,
            final String bottomLine) {
        final TokenizedEntry scannedEntry = new TokenizedEntry();
        for (int startIndex = 0; startIndex < topLine.length(); startIndex += WIDTH_OF_GLYPH) {
            final int endIndex = startIndex + WIDTH_OF_GLYPH;
            final String glyphText = topLine.substring(startIndex, endIndex)
                    + middleLine.substring(startIndex, endIndex)
                    + bottomLine.substring(startIndex, endIndex);
            scannedEntry.add(this.glyphScanner.scanGlyph(glyphText));
        }
        return scannedEntry;
    }

    private static int getLengthOfLongestLine(final String topLine, final String middleLine,
            final String bottomLine) {
        int length = Math.max(Math.max(topLine.length(), middleLine.length()), bottomLine.length());

        while (length % WIDTH_OF_GLYPH > 0) {
            length += 1;
        }

        return length;
    }

    public static String padRight(final String line, final int length) {
        return String.format("%1$-" + length + "s", line);
    }

    public GlyphScanner getGlyphScanner() {
        return this.glyphScanner;
    }
}
