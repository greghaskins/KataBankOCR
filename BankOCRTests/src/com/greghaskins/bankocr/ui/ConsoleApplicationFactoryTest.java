package com.greghaskins.bankocr.ui;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import com.greghaskins.bankocr.ui.ApplicationFactory;
import com.greghaskins.bankocr.ui.ConsoleApplication;
import com.greghaskins.bankocr.ui.ConsoleApplicationFactory;

public class ConsoleApplicationFactoryTest {
    private ConsoleApplicationFactory factory;

    @Before
    public void setUp() throws Exception {
        this.factory = new ConsoleApplicationFactory();
    }

    @Test
    public void implementsInterface() throws Exception {
        assertThat(this.factory, is(instanceOf(ApplicationFactory.class)));
    }

    @Test
    public void implementsApplicationFactoryInterface() throws Exception {
        assertThat(this.factory, is(instanceOf(ApplicationFactory.class)));
    }

    @Test
    public void buildsConsoleApplication() throws Exception {
        assertThat(this.factory.buildApplication(), is(instanceOf(ConsoleApplication.class)));
    }

}
