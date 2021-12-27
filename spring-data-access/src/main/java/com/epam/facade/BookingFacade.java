package com.epam.facade;

import com.epam.exception.UserNotFoundException;
import com.epam.model.Category;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;

import java.util.Date;
import java.util.List;

/**
 * Groups together all operations related to tickets booking.
 * Created by maksym_govorischev.
 */
public interface BookingFacade {

    /**
     * Gets user by its id.
     *
     * @return User.
     */
    User getUserById(long userId);

    /**
     * Gets user by its email. Email is strictly matched.
     *
     * @return User.
     */
    User getUserByEmail(String email) throws UserNotFoundException;

    /**
     * Get list of users by matching name. Name is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     *
     * @param name Users name or it's part
     * @return List of users.
     */
    List<User> getUsersByName(String name);

    /**
     * Creates new user. User id should be auto-generated.
     *
     * @param user User data.
     * @return Created User object.
     */
    User createUser(User user);

    /**
     * Updates user using given data.
     *
     * @param user User data for update. Should have id set.
     * @return Updated User object.
     */
    User updateUser(User user);

    /**
     * Deletes user by its id.
     *
     * @param userId User id.
     */
    void deleteUser(long userId);

    List<User> getAllUsers();


    /**
     * Gets event by its id.
     *
     * @return Event.
     */
    Event getEventById(long eventId);

    /**
     * Get list of events by matching title. Title is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     *
     * @param title Event title or it's part.
     * @return List of events.
     */
    List<Event> getEventsByTitle(String title);

    /**
     * Get list of events for specified day.
     * In case nothing was found, empty list is returned.
     *
     * @param day Date object from which day information is extracted.
     * @return List of events.
     */
    List<Event> getEventsForDay(Date day);

    /**
     * Creates new event. Event id should be auto-generated.
     *
     * @param event Event data.
     * @return Created Event object.
     */
    Event createEvent(Event event);

    /**
     * Updates event using given data.
     *
     * @param event Event data for update. Should have id set.
     * @return Updated Event object.
     */
    Event updateEvent(Event event);

    /**
     * Deletes event by its id.
     *
     * @param eventId Event id.
     */
    void deleteEvent(long eventId);

    List<Event> getAllEvents();


    /**
     * Book ticket for a specified event on behalf of specified user.
     *
     * @param userId   User Id.
     * @param eventId  Event Id.
     * @param place    Place number.
     * @param category Service category.
     * @return Booked ticket object.
     * @throws IllegalStateException if this place has already been booked.
     */
    Ticket bookTicket(long userId, long eventId, int place, Category category);

    /**
     * Get all booked tickets for specified user. Tickets should be sorted by event date in descending order.
     *
     * @param user User
     * @return List of Ticket objects.
     */
    List<Ticket> getBookedTickets(User user);

    /**
     * Get all booked tickets for specified event. Tickets should be sorted in by user email in ascending order.
     *
     * @param event Event
     * @return List of Ticket objects.
     */
    List<Ticket> getBookedTickets(Event event);

    /**
     * Cancel ticket with a specified id.
     *
     * @param ticketId Ticket id.
     */
    void cancelTicket(long ticketId);

    List<Ticket> getAllTickets();

}
