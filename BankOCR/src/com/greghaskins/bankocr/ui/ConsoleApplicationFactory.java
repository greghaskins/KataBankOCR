package com.greghaskins.bankocr.ui;

import com.greghaskins.bankocr.control.AccountEntryLexer;
import com.greghaskins.bankocr.control.AccountEntryParser;
import com.greghaskins.bankocr.control.GlyphScanner;
import com.greghaskins.bankocr.model.ChecksumCalculator;

class ConsoleApplicationFactory implements ApplicationFactory {

    @Override
    public ConsoleApplication buildApplication() {
        final AccountEntryLexer lexer = new AccountEntryLexer(new GlyphScanner());
        final AccountEntryParser parser = new AccountEntryParser();
        final ChecksumCalculator checksumCalculator = new ChecksumCalculator();
        return new ConsoleApplication(lexer, parser, checksumCalculator);
    }

}
