package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailServerTest {

    private ByteArrayInputStream byteArrayInputStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    private MailServer mailServer = new MailServer();
    private TemplateEngine templateEngine = new TemplateEngine();
    private Client client = new Client();
    private Template template = new Template();

    @BeforeEach
    public void setUp() {
        template.setTemplate("Dear #{NAME}, this is massage about #{EVENT} notification");
        Map<String, String> map = new HashMap<>();
        client.setData(map);
    }

    @Test
    public void testSentInFileMode(){
        client.setAddresses("src/main/resources/input.txt");
        String generateMessage = templateEngine.generateMessage(template, client);

        mailServer.send("src/main/resources/", generateMessage);

        String expected = "Dear anyName, this is massage about anyEvent notification";

        Assertions.assertEquals(expected, generateMessage);
    }

    @Test
    public void testSentInConsoleMode(){
        String generateMessage = templateEngine.generateMessage(template, client);
        mailServer.send(client.getAddresses(), generateMessage);

        String expected = "Dear anyName, this is massage about anyEvent notification";

        Assertions.assertEquals(expected, generateMessage);
    }

    @BeforeEach
    public void setUpOutput(){
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    private void provideInput(){
        byteArrayInputStream = new ByteArrayInputStream("test string".getBytes());
        System.setIn(byteArrayInputStream);
    }

    private String getOutput(){
        return byteArrayOutputStream.toString();
    }

    @Test
    public void mailServerShouldSendMessageToConsole() {
        final String testString = "test string";
        provideInput();

        MailServer mailServer = new MailServer();
        mailServer.send("", testString);

        assertEquals(testString, getOutput());
    }
}
