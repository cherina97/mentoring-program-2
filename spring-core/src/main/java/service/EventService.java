package service;

import exception.EventNotFoundException;
import model.Event;

import java.util.Date;
import java.util.List;

public interface EventService {

    Event getById(long id);

    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);

    Event createEvent(Event event) throws EventNotFoundException;

    Event updateEvent(long id, Event event);

    boolean deleteEvent(long eventId);
}
