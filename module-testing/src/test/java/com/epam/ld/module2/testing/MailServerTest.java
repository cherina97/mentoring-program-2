package com.epam.ld.module2.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailServerTest {

    private ByteArrayInputStream byteArrayInputStream;
    private ByteArrayOutputStream byteArrayOutputStream;

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
