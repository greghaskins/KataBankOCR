package com.greghaskins.bankocr.ui;

import java.io.InputStream;
import java.io.PrintStream;

interface Application {

    void run(final InputStream inputStream, final PrintStream outputStream);

}
