package com.greghaskins.bankocr.control;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.junit.Test;

import com.greghaskins.bankocr.control.SimpleInputStreamReader;

public class SimpleInputStreamReaderTest {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Test
    public void extendsInputStreamReader() throws Exception {
        assertThat(new SimpleInputStreamReader(new ByteArrayInputStream(new byte[0])),
                instanceOf(InputStreamReader.class));
    }

    @Test
    public void canGetWrappedInputStream() throws Exception {
        final InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        final SimpleInputStreamReader streamReader = new SimpleInputStreamReader(inputStream);
        try {
            assertThat(streamReader.getInputStream(), equalTo(inputStream));
        } finally {
            streamReader.close();
        }
    }

    @Test
    public void readsFromInputStream() throws Exception {
        final String streamContents = "this is the first line\n" + "and here is the second\n";

        final byte[] inputBytes = streamContents.getBytes(UTF8);
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(inputBytes);

        final SimpleInputStreamReader reader = new SimpleInputStreamReader(inputStream);
        try {

            final char[] readContent = new char[streamContents.length()];
            reader.read(readContent);
            assertThat(readContent, equalTo(streamContents.toCharArray()));
        } finally {
            reader.close();
        }
    }

    @Test
    public void usesUTF8Encoding() throws Exception {
        final SimpleInputStreamReader reader = new SimpleInputStreamReader(
                new ByteArrayInputStream(new byte[0]));
        try {
            assertThat(reader.getEncoding(), equalTo("UTF8"));
        } finally {
            reader.close();
        }
    }
}
