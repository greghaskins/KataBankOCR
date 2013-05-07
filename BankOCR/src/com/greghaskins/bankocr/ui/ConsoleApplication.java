package com.greghaskins.bankocr.ui;

import java.io.BufferedReader;
import java.io.PrintStream;

class ConsoleApplication implements Application {

    @Override
    public void run(final BufferedReader inputStream, final PrintStream outputStream) {
        outputStream.println("Hello World.");
    }

}
