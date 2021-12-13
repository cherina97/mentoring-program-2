package com.epam.service.impl;

import com.epam.dao.TicketDao;
import com.epam.exception.TicketNotFoundException;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import com.epam.model.impl.TicketImpl;
import com.epam.service.TicketService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private static final Log LOGGER = LogFactory.getLog(TicketServiceImpl.class);
    private final TicketDao ticketDao;

    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    //todo add validation for user id and event id
    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        Optional<Long> maxId = ticketDao.getAllTickets().stream()
                .max(Comparator.comparing(Ticket::getId))
                .map(Ticket::getId);

        Ticket ticket = new TicketImpl(eventId, userId, category, place);

        if (maxId.isPresent()) {
            ticket.setId(maxId.get() + 1L);
        } else {
            ticket.setId(1L);
        }

        return ticketDao.createTicket(ticket);
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

    @Override
    public List<Ticket> getAllTickets() {
        return ticketDao.getAllTickets();
    }
}