package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Template engine.
 */
public class TemplateEngine {

    Logger logger = Logger.getLogger(String.valueOf(TemplateEngine.class));

    public static void main(String[] args) {
        TemplateEngine templateEngine = new TemplateEngine();
        Client client = new Client();
        Template template = new Template();

        template.setTemplate("Dear #{NAME}, this is massage about #{EVENT} notification");
        Map<String, String> map = new HashMap<>();
        client.setData(map);

        System.out.println(templateEngine.generateMessage(template, client));
    }

    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        String inputTemplate = template.getTemplate();

        String regexp = "#\\{(.+?)}";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(inputTemplate);
        Scanner scanner = new Scanner(System.in);

        Map<String, String> inputMap = client.getData();

        //todo add validation
        while (matcher.find()) {
            logger.info("Please enter " + matcher.group(1).toLowerCase() + " :");
            inputMap.put(matcher.group(1), scanner.nextLine());
        }

        StringSubstitutor sub = new StringSubstitutor(inputMap, "#{", "}");
        return sub.replace(inputTemplate);
    }
}
