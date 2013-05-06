package com.greghaskins.bankocr.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class SevenSegmentGlyphTest {

    @Test
    public void getActivatedSegments() throws Exception {
        final Set<Segment> activatedSegments = makeRandomSetOfSegments();

        final SevenSegmentGlyph glyph = new SevenSegmentGlyph(activatedSegments);
        assertThat(glyph.getActivatedSegments(), equalTo(activatedSegments));
    }

    @Test
    public void toStringShowsActivatedSegments() throws Exception {
        final Set<Segment> activatedSegments = new HashSet<Segment>(Arrays.asList(
                Segment.BOTTOM_LEFT, Segment.CENTER, Segment.TOP_RIGHT));
        final SevenSegmentGlyph glyph = new SevenSegmentGlyph(activatedSegments);

        assertThat(glyph.toString(),
                equalTo("<SevenSegmentGlyph [TOP_RIGHT, BOTTOM_LEFT, CENTER]>"));
    }

    @Test
    public void instancesEqualWhenActivatedSegmentsAreEquivalent() throws Exception {
        final Set<Segment> activatedSegments = makeRandomSetOfSegments();
        final Set<Segment> equivalentActivatedSegments = new HashSet<Segment>(activatedSegments);

        final SevenSegmentGlyph originalGlyph = new SevenSegmentGlyph(activatedSegments);
        final SevenSegmentGlyph equivalentGlyph = new SevenSegmentGlyph(equivalentActivatedSegments);

        assertThat(equivalentGlyph, equalTo(originalGlyph));
    }

    @Test
    public void instancesEqualWhenBothHaveNullActivatedSegments() throws Exception {
        assertThat(new SevenSegmentGlyph(null), equalTo(new SevenSegmentGlyph(null)));
    }

    @Test
    public void hashCodesEqualWhenActivatedSegmentsAreEquivalent() throws Exception {
        final Set<Segment> activatedSegments = makeRandomSetOfSegments();
        final Set<Segment> equivalentActivatedSegments = new HashSet<Segment>(activatedSegments);

        final SevenSegmentGlyph originalGlyph = new SevenSegmentGlyph(activatedSegments);
        final SevenSegmentGlyph equivalentGlyph = new SevenSegmentGlyph(equivalentActivatedSegments);

        assertThat(equivalentGlyph.hashCode(), equalTo(originalGlyph.hashCode()));
    }

    @Test
    public void hashCodeIsZeroWhenActivatedSegmentsIsNull() throws Exception {
        assertThat(new SevenSegmentGlyph(null).hashCode(), equalTo(0));
    }

    @Test
    public void hashCodeComesFromActivatedSegmentsWhenNotNull() throws Exception {
        final Set<Segment> activatedSegments = makeRandomSetOfSegments();
        final SevenSegmentGlyph glyph = new SevenSegmentGlyph(activatedSegments);
        assertThat(glyph.hashCode(), equalTo(activatedSegments.hashCode()));
    }

    @Test
    public void instancesNotEqualWhenActivatedSegmentsDiffer() throws Exception {
        final Set<Segment> originalSegments = makeSet(Segment.TOP, Segment.BOTTOM_RIGHT,
                Segment.BOTTOM);
        final Set<Segment> differentSegments = makeSet(Segment.TOP, Segment.TOP_LEFT);

        final SevenSegmentGlyph originalGlyph = new SevenSegmentGlyph(originalSegments);
        final SevenSegmentGlyph differentGlyph = new SevenSegmentGlyph(differentSegments);

        assertThat(differentGlyph, not(equalTo(originalGlyph)));
    }

    @Test
    public void notEqualToNull() throws Exception {
        final SevenSegmentGlyph glyph = new SevenSegmentGlyph(makeRandomSetOfSegments());
        assertThat(glyph, not(equalTo(null)));
    }

    @Test
    public void notEqualToOtherTypesOfObjects() throws Exception {
        final Object glyph = new SevenSegmentGlyph(makeRandomSetOfSegments());
        assertThat(glyph, not(equalTo(new Object())));
    }

    private static <T> Set<T> makeSet(final T... items) {
        return new HashSet<T>(Arrays.asList(items));
    }

    private static Set<Segment> makeRandomSetOfSegments() {
        final int minimumNumberOfElements = 2;
        final Segment[] allSegments = Segment.values();
        final Random random = new Random();
        final int randomNumberOfElements = randomIntInRange(random, minimumNumberOfElements,
                allSegments.length);

        return makeRandomSetOfSegments(random, randomNumberOfElements);
    }

    private static Set<Segment> makeRandomSetOfSegments(final Random random,
            final int numberOfElements) {
        final Segment[] allSegments = Segment.values();
        final HashSet<Segment> randomSegments = new HashSet<Segment>();

        while (randomSegments.size() < numberOfElements) {
            final int randomIndex = random.nextInt(allSegments.length);
            randomSegments.add(allSegments[randomIndex]);
        }
        return randomSegments;
    }

    private static int randomIntInRange(final Random random, final int minimum, final int maximum) {
        return random.nextInt(maximum - minimum) + minimum;
    }
}
