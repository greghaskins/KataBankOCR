package com.greghaskins.bankocr.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class SevenSegmentGlyph {

    private final Set<Segment> activatedSegments;

    public SevenSegmentGlyph(final Set<Segment> activatedSegments) {
        this.activatedSegments = activatedSegments;

    }

    public Set<Segment> getActivatedSegments() {
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
        // TODO Auto-generated method stub
        final ArrayList<Segment> sortedActivatedSegments = new ArrayList<Segment>(
                this.activatedSegments);
        Collections.sort(sortedActivatedSegments);
        return String.format("<SevenSegmentGlyph %s>", sortedActivatedSegments);
    }
}
