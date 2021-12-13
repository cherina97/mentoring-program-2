package com.epam.controller;

import com.epam.facade.BookingFacade;
import com.epam.model.Ticket;
import com.epam.model.User;
import com.epam.model.impl.UserImpl;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/tickets/pdf")
public class TicketControllerPDF {

    public static final String PDF_VIEW = "pdfView";
    private final BookingFacade bookingFacade;

    public TicketControllerPDF(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public ModelAndView getBookedTicketsByUser(
            //simulate user creation
            @RequestParam(required = false, defaultValue = "1") int id,
            @RequestParam(required = false, defaultValue = "Name") String name,
            @RequestParam(required = false, defaultValue = "Email@mail.com") String email,

            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "1") int pageNum) {

        User user = new UserImpl(id, name, email);
        List<Ticket> bookedTickets = bookingFacade.getBookedTickets(user, pageSize, pageNum);

        //"tickets" pass to GeneratePdfReportService
        return new ModelAndView(PDF_VIEW, "tickets", bookedTickets);
    }
}
