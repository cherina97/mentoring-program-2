package com.epam.ld.module2.testing;

import java.io.*;
import java.nio.charset.StandardCharsets;

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
     * @param addresses      the addresses
     * @param messageContent the message content
     */
    public void send(String addresses, String messageContent) {
        System.out.print(messageContent);
        this.messageSent = messageContent;

        if (addresses != null) {
            writeToFile(addresses, messageContent);
        }
    }

    private void writeToFile(String addresses, String messageContent) {
        String pathToFile = addresses + "output.txt";

        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(pathToFile), StandardCharsets.UTF_8))) {
            writer.write(messageContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
