package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

public class TemplateEngineTest {

    private static final String EXPECTED = "Dear anyName, this is massage about anyEvent notification";

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
    public void generateTemplateTestInFileMode() {
        client.setAddresses("src/main/resources/input.txt");
        String actual = templateEngine.generateMessage(template, client);

        Assertions.assertEquals(EXPECTED, actual);
    }

    @Test
    public void generateTemplateTestInConsoleMode() {
        ByteArrayInputStream in = new ByteArrayInputStream("anyName\nanyEvent\n".getBytes());
        System.setIn(in);

        Assertions.assertEquals(
                EXPECTED,
                templateEngine.generateMessage(template, client));
    }

    @Test
    public void messageCantBeNull() {
        ByteArrayInputStream in = new ByteArrayInputStream("anyName\nanyEvent\n".getBytes());
        System.setIn(in);

        String message = templateEngine.generateMessage(template, client);

        Assertions.assertNotNull(message);
    }

    @Test
    public void testExpectedException() {
        Assertions.assertThrows(RuntimeException.class,
                () -> templateEngine.generateMessage(new Template(), new Client()),
                "Expected RuntimeException to throw, but it didn't");
    }

    @Test
    public void messageCantBeEmpty() {
        ByteArrayInputStream in = new ByteArrayInputStream("anyName\nanyEvent\n".getBytes());
        System.setIn(in);

        String message = templateEngine.generateMessage(template, client);

        MatcherAssert.assertThat(message, not(isEmptyString()));
    }
}
