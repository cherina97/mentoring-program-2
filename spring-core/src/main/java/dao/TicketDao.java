package dao;

import model.Ticket;

import java.util.List;

public interface TicketDao {

    Ticket createTicket(Ticket ticket);

    Ticket readTicket(long id);

    Ticket updateTicket(Ticket ticket);

    Ticket deleteTicket(long id);

    List<Ticket> getAllTickets();
}
