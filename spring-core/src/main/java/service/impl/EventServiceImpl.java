package service.impl;

import dao.EventDao;
import model.Event;
import service.EventService;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EventServiceImpl implements EventService {

    private final EventDao eventDao;

    //constructor injection
    public EventServiceImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public Event getById(long id) {
        return eventDao.readEvent(id);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventDao.getAllEvents()
                .stream()
                .filter(event -> event.getTitle().equals(title))
                .sorted(Comparator.comparing(Event::getId))
                .skip(((long) pageSize * pageNum) - pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        int dayOfWeekFromDate = getDayOfWeekFromDate(day);

        return eventDao.getAllEvents()
                .stream()
                .filter(event -> {
                    int dayOfWeekFromDateEvent = getDayOfWeekFromDate(event.getDate());
                    return dayOfWeekFromDateEvent == dayOfWeekFromDate;
                })
                .sorted(Comparator.comparing(Event::getId))
                .skip(((long) pageSize * pageNum) - pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    private int getDayOfWeekFromDate(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public Event createEvent(Event event) {
        event.setId(4);
        eventDao.createEvent(event);

        return eventDao.readEvent(4);
    }

    @Override
    public Event updateEvent(long id, Event event) {
        eventDao.updateEvent(id, event);

        return eventDao.readEvent(id);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        Event event = eventDao.deleteEvent(eventId);
        return event != null;
    }
}
