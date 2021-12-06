package controller;

import facade.BookingFacade;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {

    private final BookingFacade bookingFacade;

    public TicketController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    //bookTicket
    //getBookedTickets
    //getBookedTickets
    //cancelTicket

}
