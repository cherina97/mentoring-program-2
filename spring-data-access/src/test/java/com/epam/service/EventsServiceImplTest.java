package com.epam.service;

import com.epam.model.Event;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@NoArgsConstructor
public class EventsServiceImplTest {

    @Autowired
    private EventService eventService;


    @Test
    public void getEventByIdTest() {
        Assertions.assertEquals(1L, eventService.getById(1L).getId());
    }

    @Test
    public void getEventByTitleTest() {
        Assertions.assertEquals(2, eventService.getEventsByTitle("title1").size());
    }

    @Test
    public void createEventTest() {
        Event event = new Event("title4", new Date());
        Assertions.assertEquals(event, eventService.createEvent(event));
    }

    @Test
    public void updateUserTest() {
        Event byId = eventService.getById(1L);
        byId.setTitle("update");

        Assertions.assertEquals(byId, eventService.updateEvent(byId));
    }

    @Test
    public void deleteUserTest() {
        int size = eventService.getAllEvents().size();
        eventService.deleteEvent(3);
        Assertions.assertEquals(2, size - 1);
    }
}
