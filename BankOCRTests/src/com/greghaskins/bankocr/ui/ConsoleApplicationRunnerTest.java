package com.greghaskins.bankocr.ui;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import com.greghaskins.bankocr.control.SimpleBufferedReader;
import com.greghaskins.bankocr.control.SimpleInputStreamReader;

public class ConsoleApplicationRunnerTest {

    private ApplicationFactory mockApplicationFactory;
    private static ApplicationFactory originalApplicationFactory;
    private Application mockApplication;

    @BeforeClass
    public static void fixtureSetUp() {
        originalApplicationFactory = ConsoleApplicationRunner.getApplicationFactory();
    }

    @AfterClass
    public static void fixtureTearDown() {
        originalApplicationFactory = null;
    }

    @Before
    public void setUp() {
        this.mockApplicationFactory = mock(ApplicationFactory.class);
        ConsoleApplicationRunner.setApplicationFactory(this.mockApplicationFactory);
        this.mockApplication = mock(Application.class);
        when(this.mockApplicationFactory.buildApplication()).thenReturn(this.mockApplication);
    }

    @After
    public void tearDown() {
        ConsoleApplicationRunner.setApplicationFactory(originalApplicationFactory);
    }

    @Test
    public void runsApplicationWithStandardInputStreamWrappedInBufferedReader() throws Exception {
        ConsoleApplicationRunner.main(new String[0]);

        final ArgumentCaptor<BufferedReader> bufferedReaderCaptor = ArgumentCaptor
                .forClass(BufferedReader.class);
        verify(this.mockApplication).run(bufferedReaderCaptor.capture(), any(PrintStream.class));

        assertThat(bufferedReaderCaptor.getValue(), instanceOf(SimpleBufferedReader.class));

        final Reader reader = ((SimpleBufferedReader) bufferedReaderCaptor.getValue()).getReader();
        assertThat(reader, instanceOf(SimpleInputStreamReader.class));
        assertThat(((SimpleInputStreamReader) reader).getInputStream(), equalTo(System.in));
    }

    @Test
    public void closesBufferedReaderAfterRunningApplication() throws Exception {
        final InputStream originalInputStream = System.in;
        try {

            final InputStream mockInputStream = mock(InputStream.class);
            System.setIn(mockInputStream);

            final InOrder inOrder = inOrder(this.mockApplication, mockInputStream);

            ConsoleApplicationRunner.main(new String[0]);

            inOrder.verify(this.mockApplication).run(any(BufferedReader.class),
                    any(PrintStream.class));
            inOrder.verify(mockInputStream).close();

        } finally {
            System.setIn(originalInputStream);
        }

    }

    @Test
    public void runsApplicationWithStandardOutputStream() throws Exception {
        ConsoleApplicationRunner.main(new String[0]);

        verify(this.mockApplication).run(any(BufferedReader.class), argThat(equalTo(System.out)));
    }

    @Test
    public void printsStackTraceIfIOExceptionOccurs() throws Exception {
        final IOException mockIOException = mock(IOException.class);
        doThrow(mockIOException).when(this.mockApplication).run(any(BufferedReader.class),
                any(PrintStream.class));

        ConsoleApplicationRunner.main(new String[0]);

        verify(mockIOException).printStackTrace();
    }

    @Test
    public void canSetAndGetApplicationFactoryStatically() throws Exception {
        final ApplicationFactory applicationFactory = mock(ApplicationFactory.class);
        ConsoleApplicationRunner.setApplicationFactory(applicationFactory);

        assertThat(ConsoleApplicationRunner.getApplicationFactory(), equalTo(applicationFactory));
    }

    @Test
    public void hasConsoleApplicationFactoryByDefault() throws Exception {
        assertThat(originalApplicationFactory, is(instanceOf(ConsoleApplicationFactory.class)));
    }

}
