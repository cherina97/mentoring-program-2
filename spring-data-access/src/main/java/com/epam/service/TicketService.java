package com.epam.service;

import com.epam.model.Category;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;

import java.util.List;

public interface TicketService {

    Ticket bookTicket(long userId, long eventId, int place, Category category);

    List<Ticket> getBookedTickets(User user);

    List<Ticket> getBookedTickets(Event event);

    void cancelTicket(long ticketId);

    List<Ticket> getAllTickets();
}
