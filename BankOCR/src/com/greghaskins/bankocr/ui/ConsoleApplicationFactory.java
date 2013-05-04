package com.greghaskins.bankocr.ui;

class ConsoleApplicationFactory implements ApplicationFactory {

    @Override
    public Application buildApplication() {
        return new ConsoleApplication();
    }

}
