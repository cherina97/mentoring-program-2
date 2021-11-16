package dao;

import model.Event;
import model.Ticket;

public interface TicketDao {

    Ticket createTicket(Ticket ticket);

    Ticket readTicket(long id);

    Ticket updateTicket(Ticket ticket);

    Ticket deleteTicket(long id);
}
