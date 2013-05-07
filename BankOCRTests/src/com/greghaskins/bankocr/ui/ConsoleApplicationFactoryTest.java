package com.greghaskins.bankocr.ui;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.Before;
import org.junit.Test;

import com.greghaskins.bankocr.control.AccountEntryLexer;
import com.greghaskins.bankocr.control.AccountEntryParser;
import com.greghaskins.bankocr.control.GlyphScanner;

public class ConsoleApplicationFactoryTest {
    private ConsoleApplicationFactory factory;

    @Before
    public void setUp() throws Exception {
        this.factory = new ConsoleApplicationFactory();
    }

    @Test
    public void implementsApplicationFactoryInterface() throws Exception {
        assertThat(this.factory, instanceOf(ApplicationFactory.class));
    }

    @Test
    public void buildsConsoleApplicationWithLexerAndParser() throws Exception {
        final ConsoleApplication application = this.factory.buildApplication();
        assertThat(application, instanceOf(ConsoleApplication.class));

        assertThat(application.getLexer(), instanceOf(AccountEntryLexer.class));
        assertThat(application.getLexer().getGlyphScanner(), instanceOf(GlyphScanner.class));

        assertThat(application.getParser(), instanceOf(AccountEntryParser.class));
    }

}
