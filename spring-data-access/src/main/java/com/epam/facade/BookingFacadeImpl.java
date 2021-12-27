//package com.epam.facade;
//
//import com.epam.exception.UserNotFoundException;
//import com.epam.model.User;
//import com.epam.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class BookingFacadeImpl implements BookingFacade {
//
//    private final UserService userService;
//
//    @Autowired
//    public BookingFacadeImpl(UserService userService) {
//        this.userService = userService;
//    }
//
////    @Override
////    public Event getEventById(long eventId) {
////        return eventService.getById(eventId);
////    }
////
////    @Override
////    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
////        return eventService.getEventsByTitle(title, pageSize, pageNum);
////    }
////
////    @Override
////    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
////        return eventService.getEventsForDay(day, pageSize, pageNum);
////    }
////
////    @Override
////    public Event createEvent(Event event) {
////        return eventService.createEvent(event);
////    }
////
////    @Override
////    public Event updateEvent(long id, Event event) {
////        return eventService.updateEvent(id, event);
////    }
////
////    @Override
////    public boolean deleteEvent(long eventId) {
////        return eventService.deleteEvent(eventId);
////    }
//
//    @Override
//    public User getUserById(long userId) {
//        return userService.getUserById(userId);
//    }
//
//    @Override
//    public User getUserByEmail(String email) throws UserNotFoundException {
//        return userService.getUserByEmail(email);
//    }
//
//    @Override
//    public List<User> getUsersByName(String name) {
//        return userService.getUsersByName(name);
//    }
//
//    @Override
//    public User createUser(User user) {
//        return userService.createUser(user);
//    }
//
//    @Override
//    public User updateUser(User user) {
//        return userService.updateUser(user);
//    }
//
//    @Override
//    public void deleteUser(long userId) {
//        userService.deleteUser(userId);
//    }
//
////    @Override
////    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
////        return ticketService.bookTicket(userId, eventId, place, category);
////    }
////
////    @Override
////    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
////        return ticketService.getBookedTickets(user, pageSize, pageNum);
////    }
////
////    @Override
////    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
////        return ticketService.getBookedTickets(event, pageSize, pageNum);
////    }
////
////    @Override
////    public boolean cancelTicket(long ticketId) {
////        return ticketService.cancelTicket(ticketId);
////    }
//
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
////    @Override
////    public List<Event> getAllEvents() {
////        return eventService.getAllEvents();
////    }
////
////    @Override
////    public List<Ticket> getAllTickets() {
////        return ticketService.getAllTickets();
////    }
//
//}
