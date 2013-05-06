package com.greghaskins.bankocr.model;

public class SegmentTest extends EnumTestCase<Segment> {

    private static final String[] EXPECTED_VALUES = new String[] { "TOP", "TOP_RIGHT",
            "BOTTOM_RIGHT", "BOTTOM", "BOTTOM_LEFT", "TOP_LEFT", "CENTER" };

    @Override
    protected String[] getExpectedValues() {
        return EXPECTED_VALUES;
    }

    @Override
    protected Class<Segment> getEnumType() {
        return Segment.class;
    }

}
