package service.impl;

import dao.TicketDao;
import exception.TicketNotFoundException;
import model.Event;
import model.Ticket;
import model.User;
import model.impl.TicketImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import service.TicketService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {

    private static final Log LOGGER = LogFactory.getLog(TicketServiceImpl.class);
    private final TicketDao ticketDao;

    //constructor injection
    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        LOGGER.info("booking ticket");
        Ticket ticket = new TicketImpl(eventId, userId, category, place);

        ticket.setId(ticketDao.getAllTickets().stream()
                .max(Comparator.comparing(Ticket::getId))
                .map(Ticket::getId)
                .orElse(0L) + 1L);
        ticketDao.createTicket(ticket);

        LOGGER.info("ticket was booked " + ticket);
        return ticketDao.readTicket(ticket.getId());
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        LOGGER.info("getBookedTickets by user " + user);
        return ticketDao.getAllTickets()
                .stream()
                .filter(ticket -> ticket.getUserId() == user.getId())
                .sorted(Comparator.comparing(Ticket::getId))
                .skip(((long) pageSize * pageNum) - pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        LOGGER.info("getBookedTickets by event " + event);
        return ticketDao.getAllTickets()
                .stream()
                .filter(ticket -> ticket.getEventId() == event.getId())
                .sorted(Comparator.comparing(Ticket::getId))
                .skip(((long) pageSize * pageNum) - pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        LOGGER.info("cancelTicket " + ticketId);
        Ticket ticket = ticketDao.deleteTicket(ticketId);
        return ticket != null;
    }
}
