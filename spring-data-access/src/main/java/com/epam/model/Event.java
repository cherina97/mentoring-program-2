package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private Date date;

    @Column(name = "ticket_price")
    private BigDecimal ticketPrice;

    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public Event setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Event setTitle(String title) {
        this.title = title;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Event setDate(Date date) {
        this.date = date;
        return this;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public Event setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    @Override
    public String toString() {
        return "EventImpl{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                '}';
    }
}
