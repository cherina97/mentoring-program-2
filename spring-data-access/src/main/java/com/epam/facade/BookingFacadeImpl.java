package com.epam.facade;

import com.epam.exception.UserNotFoundException;
import com.epam.model.*;
import com.epam.service.EventService;
import com.epam.service.TicketService;
import com.epam.service.UserAccountService;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class BookingFacadeImpl implements BookingFacade {

    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;
    private final UserAccountService userAccountService;

    @Autowired
    public BookingFacadeImpl(UserService userService, EventService eventService, TicketService ticketService, UserAccountService userAccountService) {
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userAccountService = userAccountService;
    }

    @Override
    public User getUserById(long userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userService.getUsersByName(name);
    }

    @Override
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @Override
    public void deleteUser(long userId) {
        userService.deleteUser(userId);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public Event getEventById(long eventId) {
        return eventService.getById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title) {
        return eventService.getEventsByTitle(title);
    }

    @Override
    public List<Event> getEventsForDay(Date day) {
        return eventService.getEventsForDay(day);
    }

    @Override
    public Event createEvent(Event event) {
        return eventService.createEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventService.updateEvent(event);
    }

    @Override
    public void deleteEvent(long eventId) {
        eventService.deleteEvent(eventId);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        return ticketService.bookTicket(userId, eventId, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        return ticketService.getBookedTickets(user);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event) {
        return ticketService.getBookedTickets(event);
    }

    @Override
    public void cancelTicket(long ticketId) {
        ticketService.cancelTicket(ticketId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        return userAccountService.createUserAccount(userAccount);
    }

    @Override
    public UserAccount getUserAccountById(long id) {
        return userAccountService.getUserAccountById(id);
    }

    @Override
    public UserAccount getUserAccountByUserId(long userId) {
        return userAccountService.getUserAccountByUserId(userId);
    }

    @Override
    public void topUpUserAccount(long userId, BigDecimal money) {
        userAccountService.topUpUserAccount(userId, money);
    }
}
