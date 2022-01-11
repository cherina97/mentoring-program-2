package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "event_id")
    private long eventId;

    @Column(name = "user_id")
    private long userId;

    @Column
    private Category category;

    @Column
    private int place;

    public Ticket(long eventId, long userId, Category category, int place) {
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }

    public long getId() {
        return id;
    }

    public Ticket setId(long id) {
        this.id = id;
        return this;
    }

    public long getEventId() {
        return eventId;
    }

    public Ticket setEventId(long eventId) {
        this.eventId = eventId;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public Ticket setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Ticket setCategory(Category category) {
        this.category = category;
        return this;
    }

    public int getPlace() {
        return place;
    }

    public Ticket setPlace(int place) {
        this.place = place;
        return this;
    }

    @Override
    public String toString() {
        return "TicketImpl{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", userId=" + userId +
                ", category=" + category +
                ", place=" + place +
                '}';
    }
}
