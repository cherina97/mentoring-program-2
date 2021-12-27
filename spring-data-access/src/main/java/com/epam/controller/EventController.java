package com.epam.controller;

import com.epam.facade.BookingFacade;
import com.epam.model.Event;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/events")
public class EventController {

    private final BookingFacade bookingFacade;
    public static final String TEMPLATE = "eventPage";

    public EventController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createEvent(@RequestBody Event event) {
        bookingFacade.createEvent(event);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> updateEvent(@RequestBody Event event) {
        bookingFacade.updateEvent(event);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ModelAndView getEventById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        modelAndView.addObject("eventModel", bookingFacade.getEventById(id));

        return modelAndView;
    }

    @GetMapping("/getByTitle/{title}")
    public ModelAndView getEventsByTitle(@PathVariable String title) {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        modelAndView.addObject("eventModel", bookingFacade.getEventsByTitle(title));

        return modelAndView;
    }

    @GetMapping("/getByDay/{day}")
    public ModelAndView getEventsForDay(@PathVariable @DateTimeFormat(pattern = "dd.MM.yyyy") Date day) {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        modelAndView.addObject("eventModel", bookingFacade.getEventsForDay(day));

        return modelAndView;
    }

    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteEvent(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        bookingFacade.deleteEvent(id);
        modelAndView.addObject("eventModel", "Event is deleted by id = " + id);

        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        modelAndView.addObject("eventModel", bookingFacade.getAllEvents());

        return modelAndView;
    }
}
