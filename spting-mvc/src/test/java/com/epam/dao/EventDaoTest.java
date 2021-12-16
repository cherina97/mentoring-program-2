package com.epam.dao;

import com.epam.config.WebConfigurationTest;
import com.epam.model.Event;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EventDaoTest {

    ApplicationContext applicationContext;
    EventDao eventDao;
    Event event;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(WebConfigurationTest.class);
        eventDao = applicationContext.getBean(EventDao.class);

        event = Mockito.mock(Event.class);
        eventDao.createEvent(event);
    }

    @Test
    public void createEventTest() {
        Assert.assertEquals(event, eventDao.readEvent(event.getId()));
    }

    @Test
    public void readEventTest() {
        Assert.assertEquals(event, eventDao.readEvent(event.getId()));
    }

    @Test
    public void updateEventTest() {
        Assert.assertEquals(event, eventDao.updateEvent(event.getId(), event));
    }

    @Test
    public void deleteEventTest() {
        Assert.assertEquals(event, eventDao.deleteEvent(event.getId()));
    }

    @Test
    public void getAllEventsTest() {
        Assert.assertNotNull(eventDao.getAllEvents());
    }
}
