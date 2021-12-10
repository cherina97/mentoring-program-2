package com.epam.model;

import com.epam.model.impl.EventImpl;
import com.epam.model.impl.UserImpl;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

/**
 * Created by maksym_govorischev.
 */
@JsonDeserialize(as = EventImpl.class)
public interface Event {
    /**
     * Event id. UNIQUE.
     *
     * @return Event Id
     */
    long getId();

    void setId(long id);

    String getTitle();

    void setTitle(String title);

    Date getDate();

    void setDate(Date date);
}
