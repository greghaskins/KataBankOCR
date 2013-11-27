package com.greghaskins.bankocr.control;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.EnumSet;

import org.junit.Test;

import com.greghaskins.bankocr.model.Glyph;
import com.greghaskins.bankocr.model.Segment;
import com.greghaskins.bankocr.model.SevenSegmentGlyph;

public class GlyphScannerTest {

    @Test
    public void throwsIllegalArgumentExceptionIfInputIsNotNineCharactersLong() throws Exception {
        final GlyphScanner scanner = new GlyphScanner();
        try {
            scanner.scanGlyph("        ");
            scanner.scanGlyph("        ");
        } catch (final IllegalArgumentException exception) {
            assertThat(exception.getMessage(), equalTo("Glyph string must be 9 characters long."));
        }
    }

    @Test
    public void canScanTheDigitZero() throws Exception {
        final StringBuilder input = new StringBuilder();
        input.append(" _ ");
        input.append("| |");
        input.append("|_|");

        final GlyphScanner scanner = new GlyphScanner();

        assertThat(scanner.scanGlyph(input.toString()), equalTo((Glyph) SevenSegmentGlyph.ZERO));
    }

    @Test
    public void canScanTheDigitOne() throws Exception {
        final StringBuilder input = new StringBuilder();
        input.append("   ");
        input.append("  |");
        input.append("  |");

        final GlyphScanner scanner = new GlyphScanner();

        assertThat(scanner.scanGlyph(input.toString()), equalTo((Glyph) SevenSegmentGlyph.ONE));
    }

    @Test
    public void canScanTheDigitTwo() throws Exception {
        final StringBuilder input = new StringBuilder();
        input.append(" _ ");
        input.append(" _|");
        input.append("|_ ");

        final GlyphScanner scanner = new GlyphScanner();

        assertThat(scanner.scanGlyph(input.toString()), equalTo((Glyph) SevenSegmentGlyph.TWO));
    }

    @Test
    public void canScanTheDigitThree() throws Exception {
        final StringBuilder input = new StringBuilder();
        input.append(" _ ");
        input.append(" _|");
        input.append(" _|");

        final GlyphScanner scanner = new GlyphScanner();

        assertThat(scanner.scanGlyph(input.toString()), equalTo((Glyph) SevenSegmentGlyph.THREE));
    }

    @Test
    public void canScanTheDigitFour() throws Exception {
        final StringBuilder input = new StringBuilder();
        input.append("   ");
        input.append("|_|");
        input.append("  |");

        final GlyphScanner scanner = new GlyphScanner();

        assertThat(scanner.scanGlyph(input.toString()), equalTo((Glyph) SevenSegmentGlyph.FOUR));
    }

    @Test
    public void canScanTheDigitFive() throws Exception {
        final StringBuilder input = new StringBuilder();
        input.append(" _ ");
        input.append("|_ ");
        input.append(" _|");

        final GlyphScanner scanner = new GlyphScanner();

        assertThat(scanner.scanGlyph(input.toString()), equalTo((Glyph) SevenSegmentGlyph.FIVE));
    }

    @Test
    public void canScanTheDigitSix() throws Exception {
        final StringBuilder input = new StringBuilder();
        input.append(" _ ");
        input.append("|_ ");
        input.append("|_|");

        final GlyphScanner scanner = new GlyphScanner();

        assertThat(scanner.scanGlyph(input.toString()), equalTo((Glyph) SevenSegmentGlyph.SIX));
    }

    @Test
    public void canScanTheDigitSeven() throws Exception {
        final StringBuilder input = new StringBuilder();
        input.append(" _ ");
        input.append("  |");
        input.append("  |");

        final GlyphScanner scanner = new GlyphScanner();

        assertThat(scanner.scanGlyph(input.toString()), equalTo((Glyph) SevenSegmentGlyph.SEVEN));
    }

    @Test
    public void canScanTheDigitEight() throws Exception {
        final StringBuilder input = new StringBuilder();
        input.append(" _ ");
        input.append("|_|");
        input.append("|_|");

        final GlyphScanner scanner = new GlyphScanner();

        assertThat(scanner.scanGlyph(input.toString()), equalTo((Glyph) SevenSegmentGlyph.EIGHT));
    }

    @Test
    public void canScanTheDigitNine() throws Exception {
        final StringBuilder input = new StringBuilder();
        input.append(" _ ");
        input.append("|_|");
        input.append(" _|");

        final GlyphScanner scanner = new GlyphScanner();

        assertThat(scanner.scanGlyph(input.toString()), equalTo((Glyph) SevenSegmentGlyph.NINE));
    }

    @Test
    public void canScanNonNumberGlyphs_Case1() throws Exception {
        final StringBuilder input = new StringBuilder();
        input.append("   ");
        input.append(" _|");
        input.append(" _ ");

        final Glyph expectedGlyph = new SevenSegmentGlyph(EnumSet.of(Segment.CENTER,
                Segment.TOP_RIGHT, Segment.BOTTOM));

        final GlyphScanner scanner = new GlyphScanner();
        assertThat(scanner.scanGlyph(input.toString()), equalTo(expectedGlyph));
    }

    @Test
    public void canScanNonNumberGlyphs_Case2() throws Exception {
        final StringBuilder input = new StringBuilder();
        input.append(" _ ");
        input.append("|  ");
        input.append(" _|");

        final Glyph expectedGlyph = new SevenSegmentGlyph(EnumSet.of(Segment.TOP, Segment.TOP_LEFT,
                Segment.BOTTOM, Segment.BOTTOM_RIGHT));

        final GlyphScanner scanner = new GlyphScanner();
        assertThat(scanner.scanGlyph(input.toString()), equalTo(expectedGlyph));
    }

}
