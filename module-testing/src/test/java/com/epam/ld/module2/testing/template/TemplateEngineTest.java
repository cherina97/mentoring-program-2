package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
    public void generateTemplateTest(){
        String actual = templateEngine.generateMessage(template, client);
        String expected = "dfdf";

        Assertions.assertEquals(expected, actual);
    }
//
//    @Test
//    public void messageCantBeNull() {
//        String message = templateEngine.generateMessage(template, client);
//
//        Assertions.assertNotNull(message);
//    }
//
//    @Test
//    public void messageCantBeEmpty() {
//        String message = templateEngine.generateMessage(template, client);
//
//        MatcherAssert.assertThat(message, not(isEmptyString()));
//    }
//
//    @Test
//    public void templateShouldReturnValue() {
//        String value = template.getTemplate();
//
//        Assertions.assertEquals("template message", value);
//    }
//
//    @Test
//    public void messageShouldBeFromTemplate() {
//        String message = templateEngine.generateMessage(template, client);
//
//        Assertions.assertEquals(template.getTemplate(), message);
//    }

}
