package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisabledOnJre(JRE.OTHER)
public class MailServerTest {

    private final MailServer mailServer = new MailServer();
    private final TemplateEngine templateEngine = new TemplateEngine();
    private final Client client = new Client();
    private final Template template = new Template();

    @TempDir
    File file = new File("src/main/resources/");

    @BeforeEach
    public void setUp() {
        template.setTemplate("Dear #{NAME}, this is massage about #{EVENT} notification");
        Map<String, String> map = new HashMap<>();
        client.setData(map);
    }

    @Test
    @FileMode
    public void testTemporaryFolderSentInFileMode() {
        client.setAddresses("src/main/resources/input.txt");
        String generateMessage = templateEngine.generateMessage(template, client);

        mailServer.send(file.toString(), generateMessage);

        String expected = "Dear anyName, this is massage about anyEvent notification";

        Assertions.assertEquals(expected, generateMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = "src/main/resources/")
    @FileMode
    public void parameterizedTestSentInFileMode(String address) {
        client.setAddresses("src/main/resources/input.txt");
        String generateMessage = templateEngine.generateMessage(template, client);

        mailServer.send(address, generateMessage);

        String expected = "Dear anyName, this is massage about anyEvent notification";

        Assertions.assertEquals(expected, generateMessage);
    }

    @Test
    @FileMode
    public void testSentInFileMode() {
        client.setAddresses("src/main/resources/input.txt");
        String generateMessage = templateEngine.generateMessage(template, client);

        mailServer.send("src/main/resources/", generateMessage);

        String expected = "Dear anyName, this is massage about anyEvent notification";

        Assertions.assertEquals(expected, generateMessage);
    }

    @Test
    @Tag("consoleMode")
    public void testSentInConsoleMode() {
        ByteArrayInputStream in = new ByteArrayInputStream("anyName\nanyEvent\n".getBytes());
        System.setIn(in);

        String generateMessage = templateEngine.generateMessage(template, client);
        mailServer.send(client.getAddresses(), generateMessage);

        String expected = "Dear anyName, this is massage about anyEvent notification";

        Assertions.assertEquals(expected, generateMessage);
    }
}
