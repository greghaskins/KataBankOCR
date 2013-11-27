package com.greghaskins.bankocr.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public class SevenSegmentGlyph implements Glyph {

    public static final SevenSegmentGlyph ZERO = new SevenSegmentGlyph(EnumSet.of(Segment.TOP,
            Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT, Segment.BOTTOM, Segment.BOTTOM_LEFT,
            Segment.TOP_LEFT));

    public static final SevenSegmentGlyph ONE = new SevenSegmentGlyph(EnumSet.of(Segment.TOP_RIGHT,
            Segment.BOTTOM_RIGHT));

    public static final SevenSegmentGlyph TWO = new SevenSegmentGlyph(EnumSet.of(Segment.TOP,
            Segment.TOP_RIGHT, Segment.BOTTOM, Segment.BOTTOM_LEFT, Segment.CENTER));

    public static final SevenSegmentGlyph THREE = new SevenSegmentGlyph(EnumSet.of(Segment.TOP,
            Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT, Segment.BOTTOM, Segment.CENTER));

    public static final SevenSegmentGlyph FOUR = new SevenSegmentGlyph(EnumSet.of(
            Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT, Segment.TOP_LEFT, Segment.CENTER));

    public static final SevenSegmentGlyph FIVE = new SevenSegmentGlyph(EnumSet.of(Segment.TOP,
            Segment.BOTTOM_RIGHT, Segment.BOTTOM, Segment.TOP_LEFT, Segment.CENTER));

    public static final SevenSegmentGlyph SIX = new SevenSegmentGlyph(EnumSet.of(Segment.TOP,
            Segment.BOTTOM_RIGHT, Segment.BOTTOM, Segment.BOTTOM_LEFT, Segment.TOP_LEFT,
            Segment.CENTER));

    public static final SevenSegmentGlyph SEVEN = new SevenSegmentGlyph(EnumSet.of(Segment.TOP,
            Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT));

    public static final SevenSegmentGlyph EIGHT = new SevenSegmentGlyph(EnumSet.of(Segment.TOP,
            Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT, Segment.BOTTOM, Segment.BOTTOM_LEFT,
            Segment.TOP_LEFT, Segment.CENTER));

    public static final SevenSegmentGlyph NINE = new SevenSegmentGlyph(EnumSet.of(Segment.TOP,
            Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT, Segment.BOTTOM, Segment.TOP_LEFT,
            Segment.CENTER));

    private final Set<Segment> activatedSegments;

    public SevenSegmentGlyph(final Set<Segment> activatedSegments) {
        this.activatedSegments = activatedSegments;
    }

    public Iterable<Segment> getActivatedSegments() {
        return this.activatedSegments;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SevenSegmentGlyph)) {
            return false;
        }

        final SevenSegmentGlyph that = (SevenSegmentGlyph) obj;
        if (this.activatedSegments == null & that.activatedSegments == null) {
            return true;
        }
        return this.activatedSegments.equals(that.activatedSegments);
    }

    @Override
    public int hashCode() {
        if (this.activatedSegments == null) {
            return 0;
        } else {
            return this.activatedSegments.hashCode();
        }
    }

    @Override
    public String toString() {
        final ArrayList<Segment> sortedActivatedSegments = new ArrayList<Segment>(
                this.activatedSegments);
        Collections.sort(sortedActivatedSegments);
        return String.format("<SevenSegmentGlyph %s>", sortedActivatedSegments);
    }
}
