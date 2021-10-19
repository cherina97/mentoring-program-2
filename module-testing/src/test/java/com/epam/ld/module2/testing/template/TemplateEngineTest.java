package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class TemplateEngineTest {

    @Test
    public void messageCantBeNull(){
        TemplateEngine templateEngine = new TemplateEngine();
        String message = templateEngine.generateMessage(new Template(), new Client());

        assertNotNull(message);
    }

    @Test
    public void messageCantBeEmpty(){
        TemplateEngine templateEngine = new TemplateEngine();
        String message = templateEngine.generateMessage(new Template(), new Client());

        assertThat(message, not(isEmptyString()));
    }

    @Test
    public void templateShouldReturnValue(){
        Template template = new Template();
        String value = template.getMessage();

        assertEquals("Some text: #{value}", value);
    }

    @Test
    public void messageShouldBeFromTemplate(){
        TemplateEngine templateEngine = new TemplateEngine();
        Template template = new Template();
        String message = templateEngine.generateMessage(template, new Client());

        assertEquals(template.getMessage(), message);
    }

}
