//package com.epam.model;
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "tickets")
//public class Ticket {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "event_id")
//    private long eventId;
//
//    @Column(name = "user_id")
//    private long userId;
//
//    @Column
//    private Category category;
//
//    @Column
//    private int place;
//
//    public Ticket(long eventId, long userId, Category category, int place) {
//        this.eventId = eventId;
//        this.userId = userId;
//        this.category = category;
//        this.place = place;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public long getEventId() {
//        return eventId;
//    }
//
//    public void setEventId(long eventId) {
//        this.eventId = eventId;
//    }
//
//    public long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(long userId) {
//        this.userId = userId;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public int getPlace() {
//        return place;
//    }
//
//    public void setPlace(int place) {
//        this.place = place;
//    }
//
//    @Override
//    public String toString() {
//        return "TicketImpl{" +
//                "id=" + id +
//                ", eventId=" + eventId +
//                ", userId=" + userId +
//                ", category=" + category +
//                ", place=" + place +
//                '}';
//    }
//}
