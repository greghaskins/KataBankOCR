package com.greghaskins.bankocr.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

interface Application {

    void run(final BufferedReader inputStream, final PrintStream outputStream) throws IOException;

}
