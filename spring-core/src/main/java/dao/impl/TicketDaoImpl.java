package dao.impl;

import dao.TicketDao;
import model.Ticket;
import storage.Storage;

import java.util.List;

public class TicketDaoImpl implements TicketDao {

    private Storage storage;

    @Override
    public Ticket createTicket(Ticket ticket) {
        return storage.getTickets().put(ticket.getId(), ticket);
    }

    @Override
    public Ticket readTicket(long id) {
        return storage.getTickets().get(id);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return storage.getTickets().replace(ticket.getId(), ticket);
    }

    @Override
    public Ticket deleteTicket(long id) {
        return storage.getTickets().remove(id);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return (List<Ticket>) storage.getTickets().values();
    }

    //setter injection in xml
    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}