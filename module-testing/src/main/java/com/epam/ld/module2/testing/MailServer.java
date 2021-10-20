package com.epam.ld.module2.testing;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Mail server class.
 */
public class MailServer {

    private String messageSent;

    public String getMessageSent() {
        return messageSent;
    }

    /**
     * Send notification.
     *
     * @param addresses  the addresses
     * @param messageContent the message content
     */
    public void send(String addresses, String messageContent) {
        System.out.print(messageContent);
        this.messageSent = messageContent;
    }
}
