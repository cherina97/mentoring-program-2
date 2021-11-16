package dao;

import model.Event;

import java.util.List;

public interface EventDao {

    Event createEvent(Event event);

    Event readEvent(long id);

    Event updateEvent(Event event);

    Event deleteEvent(long id);

    List<Event> getAllEvents();
}
