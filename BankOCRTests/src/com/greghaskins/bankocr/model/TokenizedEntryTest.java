package com.greghaskins.bankocr.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.greghaskins.bankocr.model.Glyph;
import com.greghaskins.bankocr.model.TokenizedEntry;

public class TokenizedEntryTest {
    @Test
    public void isIterable() throws Exception {
        assertThat(new TokenizedEntry(), instanceOf(Iterable.class));
    }

    @Test
    public void canAddGlyphsAndIterateThem() throws Exception {
        final Glyph glyph1 = mock(Glyph.class);
        final Glyph glyph2 = mock(Glyph.class);
        final Glyph glyph3 = mock(Glyph.class);

        final TokenizedEntry scannedEntry = new TokenizedEntry();
        scannedEntry.add(glyph1);
        scannedEntry.add(glyph2);
        scannedEntry.add(glyph3);

        assertThat(scannedEntry, contains(glyph1, glyph2, glyph3));
    }

}
