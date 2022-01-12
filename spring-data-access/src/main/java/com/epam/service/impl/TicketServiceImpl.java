package com.epam.service.impl;

import com.epam.exception.EventNotFoundException;
import com.epam.exception.UserNotFoundException;
import com.epam.model.Category;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import com.epam.repository.EventRepository;
import com.epam.repository.TicketRepository;
import com.epam.repository.UserRepository;
import com.epam.service.TicketService;
import com.epam.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final UserAccountService userAccountService;

    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository, EventRepository eventRepository, UserAccountService userAccountService) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.userAccountService = userAccountService;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        log.info("booking ticket");

        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("such event id " + userId + " doesn't exists"));

        Event eventById = getEventById(eventId);
        log.info("withdrawing money from account for event " + eventById);
        userAccountService.withdrawMoneyFromAccount(userId, eventById.getTicketPrice());

        Ticket ticket = new Ticket(eventId, userId, category, place);

        return ticketRepository.save(ticket);
    }

    private Event getEventById(long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("such event id " + eventId + " doesn't exists"));
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        log.info("getBookedTickets by user " + user);
        return ticketRepository.findAllByUserId(user.getId());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event) {
        log.info("getBookedTickets by event " + event);
        return ticketRepository.findAllByEventId(event.getId());
    }

    @Override
    public void cancelTicket(long ticketId) {
        log.info("deleting ticket by id " + ticketId);
        ticketRepository.deleteById(ticketId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

}
