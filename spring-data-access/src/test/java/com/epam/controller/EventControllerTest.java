package com.epam.controller;

import com.epam.facade.BookingFacade;
import com.epam.model.Event;
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

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingFacade bookingFacade;

    private Event event;

    @Before
    public void setUp() {
        event = new Event("title", new Date(15 - 11 - 2021));
        bookingFacade.createEvent(event);
    }

    @Test
    public void createEventTest() throws Exception {
        mvc.perform(post("/events/new")
                        .content(asJsonString(event))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateEventTest() throws Exception {
        Event eventForUpdate = new Event("titleUpdate", new Date(15 - 11 - 2021));

        mvc.perform(post("/events/update")
                        .content(asJsonString(eventForUpdate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getEventByIdTest() throws Exception {
        mvc.perform(get("/events/getById/{id}", event.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("eventPage"))
                .andExpect(model().attribute("eventModel", bookingFacade.getEventById(event.getId())));
    }

    @Test
    public void getEventsByTitleTest() throws Exception {
        mvc.perform(get("/events/getByTitle/{title}", event.getTitle()))
                .andExpect(status().isOk())
                .andExpect(view().name("eventPage"))
                .andExpect(model().attribute("eventModel", bookingFacade.getEventsByTitle(event.getTitle())));
    }

    @Test
    public void getEventsForDayTest() throws Exception {
        String day = "15-11-2021";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(day);

        Event event1 = new Event("title", date);
        bookingFacade.createEvent(event1);

        mvc.perform(get("/events/getByDay/{day}", "15.11.2021"))
                .andExpect(status().isOk())
                .andExpect(view().name("eventPage"))
                .andExpect(model().attribute("eventModel", bookingFacade.getEventsForDay(date)));
    }

    @Test
    public void deleteEventTest() throws Exception {
        mvc.perform(delete("/events/delete/{id}", event.getId()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllEventsTest() throws Exception {
        mvc.perform(get("/events/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("eventPage"))
                .andExpect(model().attribute("eventModel", bookingFacade.getAllEvents()));
    }

    public static String asJsonString(final Event event) {
        try {
            return new ObjectMapper().writeValueAsString(event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
