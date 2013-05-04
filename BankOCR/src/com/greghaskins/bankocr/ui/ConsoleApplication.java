package com.greghaskins.bankocr.ui;

import java.io.InputStream;
import java.io.PrintStream;

class ConsoleApplication implements Application {

    @Override
    public void run(final InputStream inputStream, final PrintStream outputStream) {
        outputStream.println("Hello World.");
    }

}
