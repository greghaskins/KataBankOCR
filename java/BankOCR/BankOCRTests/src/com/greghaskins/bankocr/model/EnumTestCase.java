package com.greghaskins.bankocr.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public abstract class EnumTestCase<T extends Enum<T>> {

    protected abstract String[] getExpectedValues();

    protected abstract Class<T> getEnumType();

    @Test
    public void numberOfValues() throws Exception {
        assertThat(this.getActualValues().length, equalTo(this.getExpectedValues().length));
    }

    @Test
    public void correctValuesExist() throws Exception {
        try {
            for (final String name : this.getExpectedValues()) {
                Enum.valueOf(this.getEnumType(), name);
            }
        } catch (final IllegalArgumentException exception) {
            fail(exception.getMessage());
        }

    }

    @Test
    public void valuesAreInCorrectOrder() throws Exception {

        final List<T> segmentsInExpectedOrder = new ArrayList<T>();
        for (final String name : this.getExpectedValues()) {
            final T enumValue = Enum.valueOf(this.getEnumType(), name);
            segmentsInExpectedOrder.add(enumValue);
        }

        assertThat(this.getActualValues(), equalTo(segmentsInExpectedOrder.toArray()));
    }

    private T[] getActualValues() {
        return this.getEnumType().getEnumConstants();
    }

}
