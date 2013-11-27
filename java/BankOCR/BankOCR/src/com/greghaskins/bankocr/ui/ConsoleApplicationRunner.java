package com.greghaskins.bankocr.ui;

import java.io.IOException;

import com.greghaskins.bankocr.control.SimpleBufferedReader;
import com.greghaskins.bankocr.control.SimpleInputStreamReader;

public class ConsoleApplicationRunner {

    private static ApplicationFactory applicationFactory = new ConsoleApplicationFactory();

    static ApplicationFactory getApplicationFactory() {
        return ConsoleApplicationRunner.applicationFactory;
    }

    static void setApplicationFactory(final ApplicationFactory applicationFactory) {
        ConsoleApplicationRunner.applicationFactory = applicationFactory;
    }

    public static void main(final String[] commandLineArguments) {
        final ConsoleApplicationRunner runner = new ConsoleApplicationRunner();
        try {
            runner.buildApplicationAndRun();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void buildApplicationAndRun() throws IOException {
        final Application application = applicationFactory.buildApplication();

        final SimpleInputStreamReader inputStreamReader = new SimpleInputStreamReader(System.in);
        final SimpleBufferedReader bufferedReader = new SimpleBufferedReader(inputStreamReader);
        try {
            application.run(bufferedReader, System.out);
        } finally {
            bufferedReader.close();
            inputStreamReader.close();
        }
    }
}
