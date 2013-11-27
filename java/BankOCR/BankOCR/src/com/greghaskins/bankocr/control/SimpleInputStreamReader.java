package com.greghaskins.bankocr.control;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class SimpleInputStreamReader extends InputStreamReader {

    private final InputStream inputStream;

    public SimpleInputStreamReader(final InputStream inputStream) {
        super(inputStream, Charset.forName("UTF-8"));
        this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }
}
