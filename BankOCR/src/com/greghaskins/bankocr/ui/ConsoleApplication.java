package com.greghaskins.bankocr.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import com.greghaskins.bankocr.control.AccountEntryLexer;
import com.greghaskins.bankocr.control.AccountEntryParser;
import com.greghaskins.bankocr.model.AccountNumber;
import com.greghaskins.bankocr.model.ChecksumCalculator;
import com.greghaskins.bankocr.model.TokenizedEntry;

class ConsoleApplication implements Application {

    private final AccountEntryLexer lexer;
    private final AccountEntryParser parser;
    private final ChecksumCalculator checksumCalculator;

    public ConsoleApplication(final AccountEntryLexer lexer, final AccountEntryParser parser,
            final ChecksumCalculator checksumCalculator) {
        this.lexer = lexer;
        this.parser = parser;
        this.checksumCalculator = checksumCalculator;
    }

    @Override
    public void run(final BufferedReader inputStream, final PrintStream outputStream)
            throws IOException {

        while (true) {

            final String topLine = inputStream.readLine();
            final String middleLine = inputStream.readLine();
            final String bottomLine = inputStream.readLine();
            final String blankLine = inputStream.readLine();

            if (blankLine == null) {
                break;
            }

            final TokenizedEntry tokenizedEntry = this.lexer.tokenizeEntry(topLine, middleLine,
                    bottomLine);

            final AccountNumber accountNumber = this.parser.parseEntry(tokenizedEntry);
            outputStream.println(accountNumber.getDisplayValue(this.checksumCalculator));

        }
    }

    public AccountEntryLexer getLexer() {
        return this.lexer;
    }

    public AccountEntryParser getParser() {
        return this.parser;
    }

    public ChecksumCalculator getChecksumCalculator() {
        return this.checksumCalculator;
    }

}
