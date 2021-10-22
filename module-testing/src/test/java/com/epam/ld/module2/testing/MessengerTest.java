package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MessengerTest {

    private MailServer mailServer;
    private TemplateEngine templateEngine;
    private Messenger messenger;
    private Client client;

    @BeforeEach
    public void setUp() {
        mailServer = new MailServer();
        templateEngine = new TemplateEngine();
        messenger = new Messenger(mailServer, templateEngine);
        client = new Client();
    }

    @Test
    public void sendMessageTest() {
        client.setAddresses("address1");

        Map<String, String> map = new HashMap<>();
        map.put("NAME", "USER");
        map.put("EVENT", "SOME_EVENT");
        client.setData(map);

        Template template = new Template();
        template.setTemplate("Dear #{NAME}, this is massage about #{EVENT} notification");

        messenger.sendMessage(client, template);
        String messageSent = mailServer.getMessageSent();

        String expected = "Dear USER, this is massage about SOME_EVENT notification";
        Assertions.assertEquals(expected, messageSent);
    }

}
