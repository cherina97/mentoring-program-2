package com.epam.service.xml;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class XmlToObjectConverter {

    private Jaxb2Marshaller marshaller;
    private static final String FILE_NAME = "C:\\Users\\cheri\\IdeaProjects\\LearnJava8\\spting-mvc\\src\\main\\resources\\tickets.xml";

    public void setMarshaller(Jaxb2Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    // Converting XML to an object graph (unmarshalling)
    public List<TicketXml> unmarshallXML() throws IOException {
        TicketsListXml ticketsListXml;

        try (FileInputStream is = new FileInputStream(FILE_NAME)) {
            ticketsListXml = (TicketsListXml) this.marshaller.unmarshal(new StreamSource(is));
        }
//        System.out.println(ticketsListXml.getTickets());
        return ticketsListXml.getTickets();
    }
}
//https://www.netjstech.com/2018/11/spring-object-xml-mapping-support-jaxb-example.html
