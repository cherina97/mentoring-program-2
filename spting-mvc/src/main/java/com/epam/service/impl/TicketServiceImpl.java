package com.epam.service.impl;

import com.epam.dao.EventDao;
import com.epam.dao.TicketDao;
import com.epam.dao.UserDao;
import com.epam.exception.EventNotFoundException;
import com.epam.exception.UserNotFoundException;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import com.epam.model.impl.TicketImpl;
import com.epam.service.TicketService;
import com.epam.service.xml.TicketXml;
import com.epam.service.xml.XmlToObjectConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {

    private final UserDao userDao;
    private final EventDao eventDao;
    private final TicketDao ticketDao;
    private final XmlToObjectConverter converter;

    public TicketServiceImpl(UserDao userDao, EventDao eventDao, TicketDao ticketDao, XmlToObjectConverter converter) {
        this.userDao = userDao;
        this.eventDao = eventDao;
        this.ticketDao = ticketDao;
        this.converter = converter;
    }

    @PostConstruct
    public void init() {
        preloadTickets();
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        log.info("creating ticket");

        if (userIdIsNotPresent(userId)) {
            throw new UserNotFoundException("Such user id is not present!");
        }

        if (eventIdIsNotPresent(eventId)) {
            throw new EventNotFoundException("Such event id is not present!");
        }

        Ticket ticket = new TicketImpl(eventId, userId, category, place);
        ticket.setId(ticketDao.getAllTickets().stream()
                .max(Comparator.comparing(Ticket::getId))
                .map(Ticket::getId)
                .orElse(0L) + 1L);
        log.info("ticket was created " + ticket);

        return ticketDao.createTicket(ticket);
    }

    private boolean userIdIsNotPresent(long userId) {
        User user = userDao.readUser(userId);
        return user != null;
    }

    private boolean eventIdIsNotPresent(long eventId) {
        Event event = eventDao.readEvent(eventId);
        return event != null;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        log.info("getBookedTickets by user " + user);
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
        log.info("getBookedTickets by event " + event);
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
        log.info("cancelTicket " + ticketId);
        Ticket ticket = ticketDao.deleteTicket(ticketId);
        return ticket != null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketDao.getAllTickets();
    }

    @Override
    public void preloadTickets() {
        try {
            List<TicketXml> ticketsXml = converter.unmarshallXML();
            log.info("tickets loaded from xml: " + ticketsXml.toString());

            ticketsXml.forEach(ticket -> bookTicket(
                    ticket.getUserId(),
                    ticket.getEventId(),
                    ticket.getPlace(),
                    ticket.getCategory()));

        } catch (IOException e) {
            log.error("Error during loading tickets from xml file. " + e.getMessage());
        }
    }
}
