package dao.impl;

import dao.TicketDao;
import model.Ticket;
import org.springframework.stereotype.Repository;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao {

    private final Storage storage;

    public TicketDaoImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return storage.getTickets().put(ticket.getId(), ticket);
    }

    @Override
    public Ticket readTicket(long id) {
        return storage.getTickets().get(id);
    }

    @Override
    public Ticket updateTicket(long id, Ticket ticket) {
        return storage.getTickets().replace(id, ticket);
    }

    @Override
    public Ticket deleteTicket(long id) {
        return storage.getTickets().remove(id);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return new ArrayList<>(storage.getTickets().values());
    }
}
