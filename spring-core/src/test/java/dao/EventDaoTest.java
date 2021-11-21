package dao;

import model.Event;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventDaoTest {

    ApplicationContext applicationContext;
    EventDao eventDao;
    Event event;

    @Before
    public void setup() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        eventDao = applicationContext.getBean("eventDao", EventDao.class);

        event = Mockito.mock(Event.class);
    }

    @Test
    public void createEventTest() {
        Assert.assertNull(eventDao.createEvent(event));
        Assert.assertEquals(event, eventDao.readEvent(event.getId()));
    }

    @Test
    public void readEventTest() {
        Assert.assertNull(eventDao.createEvent(event));
        Assert.assertEquals(event, eventDao.readEvent(event.getId()));
    }

    @Test
    public void updateEventTest() {
        Assert.assertNull(eventDao.createEvent(event));
        Assert.assertEquals(event, eventDao.updateEvent(event.getId(), event));
    }

    @Test
    public void deleteEventTest() {
        Assert.assertNull(eventDao.createEvent(event));
        Assert.assertEquals(event, eventDao.deleteEvent(event.getId()));
    }

    @Test
    public void getAllEventsTest() {
        Assert.assertNull(eventDao.createEvent(event));
        Assert.assertNotNull(eventDao.getAllEvents());
    }
}
