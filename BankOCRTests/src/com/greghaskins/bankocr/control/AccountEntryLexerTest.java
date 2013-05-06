package com.greghaskins.bankocr.control;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.greghaskins.bankocr.model.Glyph;

public class AccountEntryLexerTest {

    private GlyphScanner glyphScanner;
    private AccountEntryLexer accountEntryScanner;

    @Before
    public void setUp() throws Exception {
        this.glyphScanner = mock(GlyphScanner.class);
        this.accountEntryScanner = new AccountEntryLexer(this.glyphScanner);
    }

    @Test
    public void getGlyphScanner() throws Exception {
        assertThat(this.accountEntryScanner.getGlyphScanner(), equalTo(this.glyphScanner));
    }

    @Test
    public void splitsInputIntoPiecesForEachGlpyhAndPassesThemToGlyphScanner() throws Exception {

        final String line1 = "aaa" + "ddd" + "ggg" + "jjj";
        final String line2 = "bbb" + "eee" + "hhh" + "kkk";
        final String line3 = "ccc" + "fff" + "iii" + "lll";

        final Glyph[] expectedGlyphs = makeGlyphs(4);

        stub(this.glyphScanner.scanGlyph("aaabbbccc")).toReturn(expectedGlyphs[0]);
        stub(this.glyphScanner.scanGlyph("dddeeefff")).toReturn(expectedGlyphs[1]);
        stub(this.glyphScanner.scanGlyph("ggghhhiii")).toReturn(expectedGlyphs[2]);
        stub(this.glyphScanner.scanGlyph("jjjkkklll")).toReturn(expectedGlyphs[3]);

        assertThat(this.accountEntryScanner.tokenizeEntry(line1, line2, line3),
                contains(expectedGlyphs));
    }

    @Test
    public void padsWithSpacesIfLinesAreNotTheSameLength() throws Exception {
        final String line1 = "111" + "111";
        final String line2 = "222" + "222" + "222";
        final String line3 = "33";

        final Glyph[] expectedGlyphs = makeGlyphs(3);

        stub(this.glyphScanner.scanGlyph("11122233 ")).toReturn(expectedGlyphs[0]);
        stub(this.glyphScanner.scanGlyph("111222   ")).toReturn(expectedGlyphs[1]);
        stub(this.glyphScanner.scanGlyph("   222   ")).toReturn(expectedGlyphs[2]);

        assertThat(this.accountEntryScanner.tokenizeEntry(line1, line2, line3),
                contains(expectedGlyphs));
    }

    @Test
    public void padsWithSpacesIfLinesAreNotDivisibleByThree_RemainderOne() throws Exception {
        final String line1 = "xyz" + "1";
        final String line2 = "xyz" + "2";
        final String line3 = "xyz" + "3";

        final Glyph[] expectedGlyphs = makeGlyphs(2);

        stub(this.glyphScanner.scanGlyph("xyzxyzxyz")).toReturn(expectedGlyphs[0]);
        stub(this.glyphScanner.scanGlyph("1  2  3  ")).toReturn(expectedGlyphs[1]);

        assertThat(this.accountEntryScanner.tokenizeEntry(line1, line2, line3),
                contains(expectedGlyphs));
    }

    @Test
    public void padsWithSpacesIfLinesAreNotDivisibleByThree_RemainderTwo() throws Exception {
        final String line1 = "xyz" + "11";
        final String line2 = "xyz" + "22";
        final String line3 = "xyz" + "33";

        final Glyph[] expectedGlyphs = makeGlyphs(2);

        stub(this.glyphScanner.scanGlyph("xyzxyzxyz")).toReturn(expectedGlyphs[0]);
        stub(this.glyphScanner.scanGlyph("11 22 33 ")).toReturn(expectedGlyphs[1]);

        assertThat(this.accountEntryScanner.tokenizeEntry(line1, line2, line3),
                contains(expectedGlyphs));
    }

    private static Glyph[] makeGlyphs(final int numberOfGlyphs) {
        final ArrayList<Glyph> glyphs = new ArrayList<Glyph>(numberOfGlyphs);
        for (int i = 0; i < numberOfGlyphs; i++) {
            glyphs.add(mock(Glyph.class));
        }
        return glyphs.toArray(new Glyph[0]);
    }

}
