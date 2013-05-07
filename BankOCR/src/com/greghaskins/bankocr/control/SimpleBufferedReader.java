package com.greghaskins.bankocr.control;

import java.io.BufferedReader;
import java.io.Reader;

public class SimpleBufferedReader extends BufferedReader {

    private final Reader reader;

    public SimpleBufferedReader(final Reader reader) {
        super(reader);
        this.reader = reader;
    }

    public Reader getReader() {
        return this.reader;
    }

}
