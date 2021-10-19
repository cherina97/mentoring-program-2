package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class TemplateEngineTest {
    private TemplateEngine templateEngine;
    private Template template;

    @Before
    public void setUp() {
        templateEngine = new TemplateEngine();
        template = new Template();
    }

    @Test
    public void messageCantBeNull() {
        String message = templateEngine.generateMessage(new Template(), new Client());

        assertNotNull(message);
    }

    @Test
    public void messageCantBeEmpty() {
        String message = templateEngine.generateMessage(new Template(), new Client());

        assertThat(message, not(isEmptyString()));
    }

    @Test
    public void templateShouldReturnValue() {
        String value = template.getMessage();

        assertEquals("Some text: #{value}", value);
    }

    @Test
    public void messageShouldBeFromTemplate() {
        String message = templateEngine.generateMessage(template, new Client());

        assertEquals(template.getMessage(), message);
    }

}
