package controller;

import facade.BookingFacade;
import org.springframework.stereotype.Controller;

@Controller
public class EventController {

    private final BookingFacade bookingFacade;

    public EventController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    //getEventById
    //getEventsByTitle
    //getEventsForDay
    //createEvent
    //updateEvent
    //deleteEvent

}
