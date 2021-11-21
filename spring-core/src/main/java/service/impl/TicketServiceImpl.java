package service.impl;

import dao.TicketDao;
import exception.EventNotFoundException;
import exception.TicketNotFoundException;
import model.Event;
import model.Ticket;
import model.User;
import model.impl.TicketImpl;
import service.TicketService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;

    //constructor injection
    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) throws TicketNotFoundException {
        Long maxId = ticketDao.getAllTickets().stream()
                .max(Comparator.comparing(Ticket::getId))
                .map(Ticket::getId)
                .orElseThrow(() -> new TicketNotFoundException("There are no tickets in storage"));

        Ticket ticket = new TicketImpl(eventId, userId, category, place);
        ticket.setId(maxId + 1);
        ticketDao.createTicket(ticket);

        return ticketDao.readTicket(ticket.getId());
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
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
        Ticket ticket = ticketDao.deleteTicket(ticketId);
        return ticket != null;
    }
}
