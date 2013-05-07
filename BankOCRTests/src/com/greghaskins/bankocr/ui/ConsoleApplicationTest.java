package com.greghaskins.bankocr.ui;

import static com.greghaskins.bankocr.model.AccountNumberTest.makeAccountNumber;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.greghaskins.bankocr.control.AccountEntryLexer;
import com.greghaskins.bankocr.control.AccountEntryParser;
import com.greghaskins.bankocr.model.AccountNumber;
import com.greghaskins.bankocr.model.ChecksumCalculator;
import com.greghaskins.bankocr.model.Glyph;
import com.greghaskins.bankocr.model.TokenizedEntry;

public class ConsoleApplicationTest {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private ConsoleApplication application;
    private PrintStream outputStream;
    private ByteArrayOutputStream underlyingOutputStream;
    private AccountEntryLexer mockLexer;
    private AccountEntryParser mockParser;
    private BufferedReader mockBufferedReader;
    private ChecksumCalculator mockCalculator;

    @Before
    public void setUp() throws Exception {
        this.underlyingOutputStream = new ByteArrayOutputStream();
        this.outputStream = new PrintStream(this.underlyingOutputStream);

        this.mockBufferedReader = mock(BufferedReader.class);

        this.mockLexer = mock(AccountEntryLexer.class);
        this.mockParser = mock(AccountEntryParser.class);
        this.mockCalculator = mock(ChecksumCalculator.class);
        this.application = new ConsoleApplication(this.mockLexer, this.mockParser,
                this.mockCalculator);
    }

    @After
    public void tearDown() throws Exception {
        this.outputStream.close();
        this.underlyingOutputStream.close();
    }

    @Test
    public void implementsApplicationInterface() throws Exception {
        assertThat(this.application, is(instanceOf(Application.class)));
    }

    @Test
    public void readsFourLinesAndWritesDisplayValueOfAccountNumber() throws Exception {

        final TokenizedEntry tokenizedEntry = makeTokenizedEntry(mock(Glyph.class));
        final AccountNumber accountNumber = makeAccountNumber(4, 5, 7, 5, 0, 8, 0, 0, 0);

        when(this.mockBufferedReader.readLine()).thenReturn("apples", "bananas", "cherries",
                "dragonfruit", null);
        when(this.mockLexer.tokenizeEntry("apples", "bananas", "cherries")).thenReturn(
                tokenizedEntry);
        when(this.mockParser.parseEntry(tokenizedEntry)).thenReturn(accountNumber);

        this.application.run(this.mockBufferedReader, this.outputStream);

        final String printedOutput = this.getPrintedOutput();
        assertThat(printedOutput, equalTo("457508000\n"));
    }

    @Test
    public void readsGroupsOfFourLinesAtATimeAndGeneratesOutputIteratively() throws Exception {
        final TokenizedEntry tokenizedEntry1 = makeTokenizedEntry(mock(Glyph.class));
        final TokenizedEntry tokenizedEntry2 = makeTokenizedEntry(mock(Glyph.class));
        final AccountNumber accountNumber1 = makeAccountNumber(4, 5, 7, 5, 0, 8, 0, 0, 0);
        final AccountNumber accountNumber2 = makeAccountNumber(6, 6, 4, 3, 7, 1, 4, 9, 5);

        when(this.mockBufferedReader.readLine()).thenReturn("apples", "bananas", "cherries",
                "dragonfruit", "eggplant", "figs", "grapes", "honeydew", null);
        when(this.mockLexer.tokenizeEntry("apples", "bananas", "cherries")).thenReturn(
                tokenizedEntry1);
        when(this.mockLexer.tokenizeEntry("eggplant", "figs", "grapes"))
                .thenReturn(tokenizedEntry2);
        when(this.mockParser.parseEntry(tokenizedEntry1)).thenReturn(accountNumber1);
        when(this.mockParser.parseEntry(tokenizedEntry2)).thenReturn(accountNumber2);

        this.application.run(this.mockBufferedReader, this.outputStream);

        final String printedOutput = this.getPrintedOutput();
        assertThat(printedOutput, equalTo("457508000\n" + "664371495\n"));
    }

    @Test
    public void usesChecksumCalculatorToGetAccountNumberDisplayValue() throws Exception {
        final TokenizedEntry tokenizedEntry = makeTokenizedEntry(mock(Glyph.class));
        final AccountNumber mockAccountNumber = mock(AccountNumber.class);

        when(this.mockBufferedReader.readLine()).thenReturn("bibbidi", "bobbidi", "boo", "", null);
        when(this.mockLexer.tokenizeEntry("bibbidi", "bobbidi", "boo")).thenReturn(tokenizedEntry);
        when(this.mockParser.parseEntry(tokenizedEntry)).thenReturn(mockAccountNumber);
        when(mockAccountNumber.getDisplayValue(this.mockCalculator)).thenReturn("Cinderella");

        this.application.run(this.mockBufferedReader, this.outputStream);

        final String printedOutput = this.getPrintedOutput();
        assertThat(printedOutput, equalTo("Cinderella\n"));
        verify(mockAccountNumber).getDisplayValue(this.mockCalculator);
    }

    @Test
    public void doesNotAttemptToLexOrParseIfInputHasExtraLinesNotMakingAFullEntry()
            throws Exception {
        when(this.mockBufferedReader.readLine()).thenReturn("apples", "bananas", null);

        this.application.run(this.mockBufferedReader, this.outputStream);

        verifyZeroInteractions(this.mockLexer, this.mockParser);

        final String printedOutput = this.getPrintedOutput();
        assertThat(printedOutput, equalTo(""));
    }

    @Test
    public void canGetAccountEntryLexer() throws Exception {
        assertThat(this.application.getLexer(), equalTo(this.mockLexer));
    }

    @Test
    public void canGetAccountEntryParser() throws Exception {
        assertThat(this.application.getParser(), equalTo(this.mockParser));
    }

    @Test
    public void canGetChecksumVerifier() throws Exception {
        assertThat(this.application.getChecksumCalculator(), equalTo(this.mockCalculator));
    }

    private String getPrintedOutput() {
        return new String(this.underlyingOutputStream.toByteArray(), UTF8);
    }

    private static TokenizedEntry makeTokenizedEntry(final Glyph... glyphs) {
        final TokenizedEntry tokenizedEntry = new TokenizedEntry();
        for (final Glyph glyph : glyphs) {
            tokenizedEntry.add(glyph);
        }
        return tokenizedEntry;
    }

}
