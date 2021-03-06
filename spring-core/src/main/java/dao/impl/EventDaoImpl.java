package dao.impl;

import dao.EventDao;
import model.Event;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class EventDaoImpl implements EventDao {

    private Storage storage;

    //setter injection in xml
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Event createEvent(Event event) {
        return storage.getEvents().put(event.getId(), event);
    }

    @Override
    public Event readEvent(long id) {
        return storage.getEvents().get(id);
    }

    @Override
    public Event updateEvent(long id, Event event) {
        return storage.getEvents().replace(id, event);
    }

    @Override
    public Event deleteEvent(long id) {
        return storage.getEvents().remove(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<>(storage.getEvents().values());
    }
}
