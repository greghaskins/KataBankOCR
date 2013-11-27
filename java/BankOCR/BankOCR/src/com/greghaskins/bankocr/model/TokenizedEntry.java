package com.greghaskins.bankocr.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TokenizedEntry implements Iterable<Glyph> {

    private final List<Glyph> glyphs = new ArrayList<Glyph>();

    public void add(final Glyph glyph) {
        this.glyphs.add(glyph);
    }

    @Override
    public Iterator<Glyph> iterator() {
        return this.glyphs.iterator();
    }

}
