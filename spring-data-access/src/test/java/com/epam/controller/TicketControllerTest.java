package com.epam.controller;

import com.epam.facade.BookingFacade;
import com.epam.model.Category;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingFacade bookingFacade;

    private Ticket ticket;

    @Before
    public void setUp() {
        ticket = new Ticket(1L, 1L, Category.BAR, 102);
        bookingFacade.bookTicket(ticket.getUserId(), ticket.getEventId(), ticket.getPlace(), ticket.getCategory());
    }

    @Test
    public void bookTicketTest() throws Exception {
        mvc.perform(post("/tickets/new")
                        .content(asJsonString(ticket))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getBookedTicketsByUserTest() throws Exception {
        User user = new User(1L, "userName", "email@mail.com");

        mvc.perform(get("/tickets/byUser")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("ticketPage"))
                .andExpect(model().attribute("ticketModel", bookingFacade.getBookedTickets(user)));
    }

    @Test
    public void getBookedTicketsByEventTest() throws Exception {
        Event event = new Event("title", new Date(15 - 11 - 2021));

        mvc.perform(get("/tickets/byEvent")
                        .content(asJsonString(event))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("ticketPage"))
                .andExpect(model().attribute("ticketModel", bookingFacade.getBookedTickets(event)));
    }

    @Test
    public void deleteTicketTest() throws Exception {
        mvc.perform(delete("/tickets/delete/{id}", ticket.getId()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllTicketsTest() throws Exception {
        mvc.perform(get("/tickets/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("ticketPage"))
                .andExpect(model().attribute("ticketModel", bookingFacade.getAllTickets()));
    }

    public static String asJsonString(final Ticket ticket) {
        try {
            return new ObjectMapper().writeValueAsString(ticket);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String asJsonString(final User user) {
        try {
            return new ObjectMapper().writeValueAsString(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String asJsonString(final Event event) {
        try {
            return new ObjectMapper().writeValueAsString(event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
