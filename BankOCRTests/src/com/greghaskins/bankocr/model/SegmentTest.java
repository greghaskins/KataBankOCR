package com.greghaskins.bankocr.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SegmentTest {

    private static final String[] EXPECTED_VALUES = new String[] { "TOP", "TOP_RIGHT",
            "BOTTOM_RIGHT", "BOTTOM", "BOTTOM_LEFT", "TOP_LEFT", "CENTER" };

    @Test
    public void thereAreSevenPossibleValues() throws Exception {
        assertThat(Segment.values().length, equalTo(EXPECTED_VALUES.length));
    }

    @Test
    public void correctValuesExist() throws Exception {
        try {
            for (final String name : EXPECTED_VALUES) {
                Segment.valueOf(name);
            }
        } catch (final IllegalArgumentException exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void valuesAreInClockwiseOrderStartingFromTop() throws Exception {

        final List<Segment> segmentsInExpectedOrder = new ArrayList<Segment>();
        for (final String name : EXPECTED_VALUES) {
            segmentsInExpectedOrder.add(Segment.valueOf(name));
        }

        assertThat(Segment.values(), equalTo(segmentsInExpectedOrder.toArray()));
    }
}
