package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

public class TemplateEngineTest {

    private TemplateEngine templateEngine;
    private Client client;
    private Template template;

    @BeforeEach
    public void setUp() {
        templateEngine = new TemplateEngine();
        client = new Client();
        template = new Template();

        template.setTemplate("Dear #{NAME}, this is massage about #{EVENT} notification");
        Map<String, String> map = new HashMap<>();
        client.setData(map);
    }

    @Test
    public void generateTemplateTestInFileMode(){
        client.setAddresses("src/main/resources/input.txt");
        String actual = templateEngine.generateMessage(template, client);
        String expected = "Dear anyName, this is massage about anyEvent notification";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void generateTemplateTestInConsoleMode(){
        String actual = templateEngine.generateMessage(template, client);
        String expected = "Dear anyName, this is massage about anyEvent notification";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void messageCantBeNull() {
        String message = templateEngine.generateMessage(template, client);

        Assertions.assertNotNull(message);
    }

    @Test
    public void messageCantBeEmpty() {
        String message = templateEngine.generateMessage(template, client);

        MatcherAssert.assertThat(message, not(isEmptyString()));
    }
}
