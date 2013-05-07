package com.greghaskins.bankocr.ui;

import com.greghaskins.bankocr.control.AccountEntryLexer;
import com.greghaskins.bankocr.control.AccountEntryParser;
import com.greghaskins.bankocr.control.GlyphScanner;

class ConsoleApplicationFactory implements ApplicationFactory {

    @Override
    public ConsoleApplication buildApplication() {
        final AccountEntryLexer lexer = new AccountEntryLexer(new GlyphScanner());
        final AccountEntryParser parser = new AccountEntryParser();
        return new ConsoleApplication(lexer, parser);
    }

}
