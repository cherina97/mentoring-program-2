package com.epam.config;

import com.epam.service.xml.TicketXml;
import com.epam.service.xml.TicketsListXml;
import com.epam.service.xml.XmlToObjectConverter;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@ComponentScan({"com.epam.dao.impl", "com.epam.storage", "com.epam.service"})
@PropertySource("classpath:app.properties")
@Configuration
public class WebConfigurationTest {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(
                TicketXml.class,
                TicketsListXml.class);

        return marshaller;
    }

    @Bean
    public XmlToObjectConverter xmlToObjectConverter() {
        XmlToObjectConverter xmlToObjectConverter = new XmlToObjectConverter();
        xmlToObjectConverter.setMarshaller(jaxb2Marshaller());

        return xmlToObjectConverter;
    }

}
