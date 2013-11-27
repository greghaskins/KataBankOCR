package com.greghaskins.bankocr.control;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.junit.Test;

public class SimpleBufferedReaderTest {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Test
    public void extendsBufferedReader() throws Exception {
        final SimpleBufferedReader simpleBufferedReader = new SimpleBufferedReader(
                mock(Reader.class));
        try {
            assertThat(simpleBufferedReader, instanceOf(BufferedReader.class));
        } finally {
            simpleBufferedReader.close();
        }
    }

    @Test
    public void canGetWrappedReader() throws Exception {
        final Reader reader = mock(Reader.class);
        final SimpleBufferedReader bufferedReader = new SimpleBufferedReader(reader);
        try {
            assertThat(bufferedReader.getReader(), equalTo(reader));
        } finally {
            bufferedReader.close();
        }
    }

    @Test
    public void readsLinesFromWrappedReader() throws Exception {
        final String streamContents = "content on line one\n" + "this is on the second line\n";

        final InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream(
                streamContents.getBytes(UTF8)));

        final SimpleBufferedReader bufferedReader = new SimpleBufferedReader(inputStreamReader);
        try {
            assertThat(bufferedReader.readLine(), equalTo("content on line one"));
            assertThat(bufferedReader.readLine(), equalTo("this is on the second line"));
            assertThat(bufferedReader.readLine(), nullValue());
        } finally {
            bufferedReader.close();
        }
    }

}
