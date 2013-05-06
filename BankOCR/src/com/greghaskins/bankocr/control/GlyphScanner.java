package com.greghaskins.bankocr.control;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.greghaskins.bankocr.model.Glyph;
import com.greghaskins.bankocr.model.Segment;
import com.greghaskins.bankocr.model.SevenSegmentGlyph;

public class GlyphScanner {

    private static final Map<Integer, Segment> segmentIndicesInGlyph = new HashMap<Integer, Segment>();
    static {
        segmentIndicesInGlyph.put(new Integer(1), Segment.TOP);
        segmentIndicesInGlyph.put(new Integer(3), Segment.TOP_LEFT);
        segmentIndicesInGlyph.put(new Integer(4), Segment.CENTER);
        segmentIndicesInGlyph.put(new Integer(5), Segment.TOP_RIGHT);
        segmentIndicesInGlyph.put(new Integer(6), Segment.BOTTOM_LEFT);
        segmentIndicesInGlyph.put(new Integer(7), Segment.BOTTOM);
        segmentIndicesInGlyph.put(new Integer(8), Segment.BOTTOM_RIGHT);
    }

    public Glyph scanGlyph(final String text) {
        if (text.length() != 9) {
            throw new IllegalArgumentException("Glyph string must be 9 characters long.");
        }

        final Set<Segment> segments = new HashSet<Segment>();
        for (final Entry<Integer, Segment> entry : segmentIndicesInGlyph.entrySet()) {
            final int currentIndex = entry.getKey().intValue();
            if (!characterIsBlank(text, currentIndex)) {
                segments.add(entry.getValue());
            }
        }
        return new SevenSegmentGlyph(segments);
    }

    private static boolean characterIsBlank(final String text, final int currentIndex) {
        return text.charAt(currentIndex) == ' ';
    }
}
