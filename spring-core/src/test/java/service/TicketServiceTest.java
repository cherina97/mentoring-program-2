package service;

import model.Event;
import model.Ticket;
import model.User;
import model.impl.EventImpl;
import model.impl.TicketImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import storage.Storage;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TicketServiceTest {

    ApplicationContext context;
    TicketService ticketService;
    Ticket ticket;
    Storage storage;

    @Before
    public void setup() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ticketService = context.getBean(TicketService.class);
        storage = context.getBean("storage", Storage.class);
        ticket = Mockito.mock(Ticket.class);
    }

    @Test
    public void bookTicketTest() {
        Ticket ticket = new TicketImpl(1, 1, Ticket.Category.BAR, 1);
        ticket.setId(7);

        Assert.assertEquals(ticket, ticketService.bookTicket(1, 1, 1, Ticket.Category.BAR));
    }

    @Test
    public void getBookedTicketsByUserTest(){
        User user = Mockito.mock(User.class);
        Mockito.when(user.getId()).thenReturn(1L);
        Assert.assertEquals(2, ticketService.getBookedTickets(user, 10, 1).size());
    }

    @Test
    public void getBookedTicketsByEventTest(){
        Event event = Mockito.mock(Event.class);
        Mockito.when(event.getId()).thenReturn(1L);
        Assert.assertEquals(2, ticketService.getBookedTickets(event, 10, 1).size());
    }


    @Test
    public void cancelTicketTest() {
        Assert.assertTrue(ticketService.cancelTicket(1));
    }
}
