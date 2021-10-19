package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

import java.util.Map;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        final String[] message = {template.getTemplate()};
        Map<String, String> data = client.getData();

        data.forEach((key, value) -> {
            String pattern = "#\\{" + key + "}";
            message[0] = message[0].replaceAll(pattern, value);
        });

        return message[0];
    }
}
