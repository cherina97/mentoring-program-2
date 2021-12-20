package com.epam.dao;

import com.epam.model.Ticket;

import java.util.List;

public interface TicketDao {

    Ticket createTicket(Ticket ticket);

    Ticket readTicket(long id);

    Ticket updateTicket(long id, Ticket ticket);

    Ticket deleteTicket(long id);

    List<Ticket> getAllTickets();
}
