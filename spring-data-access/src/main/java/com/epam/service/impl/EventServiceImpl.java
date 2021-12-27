package com.epam.service.impl;

import com.epam.exception.EventNotFoundException;
import com.epam.exception.GlobalApplicationException;
import com.epam.model.Event;
import com.epam.repository.EventRepository;
import com.epam.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event getById(long id) {
        log.info("getting event by id " + id);

        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found by id " + id));
    }

    @Override
    public List<Event> getEventsByTitle(String title) {
        log.info("getting events by title " + title);
        return eventRepository.findAllByTitle(title);
    }

    @Override
    public List<Event> getEventsForDay(Date day) {
        log.info("getting event by day " + day);
        int dayOfWeekFromDate = getDayOfWeekFromDate(day);

        return eventRepository.findAll()
                .stream()
                .filter(event -> {
                    int dayOfWeekFromDateEvent = getDayOfWeekFromDate(event.getDate());
                    return dayOfWeekFromDateEvent == dayOfWeekFromDate;
                })
                .sorted(Comparator.comparing(Event::getId))
                .collect(Collectors.toList());
    }

    private int getDayOfWeekFromDate(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public Event createEvent(Event event) {
        log.info("creating event:  " + event);
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        log.info("updating event " + event);

        Optional<Event> eventById = eventRepository.findById(event.getId());

        if (eventById.isPresent()) {
            eventById.get().setDate(event.getDate());
            eventById.get().setTitle(event.getTitle());

            return eventRepository.save(eventById.get());
        } else {
            throw new GlobalApplicationException("Event updating error");
        }
    }

    @Override
    public void deleteEvent(long eventId) {
        log.info("deleting event by id " + eventId);
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
