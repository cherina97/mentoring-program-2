package com.epam.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Accessors(chain = true)
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
}
