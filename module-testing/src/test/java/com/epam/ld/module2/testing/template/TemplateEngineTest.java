package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.Assert;
import org.junit.Test;

public class TemplateEngineTest {

    @Test
    public void messageCantBeNull(){
        TemplateEngine templateEngine = new TemplateEngine();
        String message = templateEngine.generateMessage(new Template(), new Client());

        Assert.assertNotNull(message);
    }

}
