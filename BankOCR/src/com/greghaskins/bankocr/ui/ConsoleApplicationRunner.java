package com.greghaskins.bankocr.ui;

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
        runner.buildApplicationAndRun();
    }

    private void buildApplicationAndRun() {
        final Application application = applicationFactory.buildApplication();
        application.run(System.in, System.out);
    }
}
