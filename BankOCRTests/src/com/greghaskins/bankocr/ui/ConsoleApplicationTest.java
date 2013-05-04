package com.greghaskins.bankocr.ui;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.greghaskins.bankocr.ui.Application;
import com.greghaskins.bankocr.ui.ConsoleApplication;

public class ConsoleApplicationTest {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private ConsoleApplication application;
    private PrintStream outputStream;
    private ByteArrayOutputStream underlyingOutputStream;

    @Before
    public void setUp() throws Exception {
        this.underlyingOutputStream = new ByteArrayOutputStream();
        this.outputStream = new PrintStream(this.underlyingOutputStream);
        this.application = new ConsoleApplication();
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
    public void writesHelloWorldToTheConsoleForNow() throws Exception {
        this.application.run(null, this.outputStream);
        final String outputText = this.getPrintedOutput();
        assertThat(outputText, is(equalTo("Hello World.\n")));
    }

    private String getPrintedOutput() {
        return new String(this.underlyingOutputStream.toByteArray(), ConsoleApplicationTest.UTF8);
    }
}
