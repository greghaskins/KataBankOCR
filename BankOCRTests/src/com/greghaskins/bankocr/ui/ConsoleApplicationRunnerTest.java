package com.greghaskins.bankocr.ui;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConsoleApplicationRunnerTest {

    private ApplicationFactory mockApplicationFactory;
    private static ApplicationFactory originalApplicationFactory;

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
    }

    @After
    public void tearDown() {
        ConsoleApplicationRunner.setApplicationFactory(originalApplicationFactory);
    }

    @Test
    public void runsApplicationWithStandardInputAndOutputStreams() throws Exception {
        final Application mockApplication = mock(Application.class);
        when(this.mockApplicationFactory.buildApplication()).thenReturn(mockApplication);

        ConsoleApplicationRunner.main(new String[0]);

        verify(mockApplication).run(System.in, System.out);
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
