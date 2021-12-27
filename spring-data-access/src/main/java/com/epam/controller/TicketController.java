package com.epam.controller;

import com.epam.facade.BookingFacade;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private static final String TEMPLATE = "ticketPage";
    private final BookingFacade bookingFacade;

    public TicketController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> bookTicket(@RequestBody Ticket ticket) {
        bookingFacade.bookTicket(ticket.getUserId(), ticket.getEventId(), ticket.getPlace(), ticket.getCategory());

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/byUser")
    public ModelAndView getBookedTicketsByUser(@RequestBody User user) {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        modelAndView.addObject("ticketModel", bookingFacade.getBookedTickets(user));

        return modelAndView;
    }

    @GetMapping("/byEvent")
    public ModelAndView getBookedTicketsByEvent(@RequestBody Event event) {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        modelAndView.addObject("ticketModel", bookingFacade.getBookedTickets(event));

        return modelAndView;
    }

    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteTicket(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        bookingFacade.cancelTicket(id);
        modelAndView.addObject("ticketModel", "Ticket is delete by id = " + id);

        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        modelAndView.addObject("ticketModel", bookingFacade.getAllTickets());

        return modelAndView;
    }
}
