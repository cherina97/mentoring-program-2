package com.epam.repository;

import com.epam.model.Event;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {

    List<Event> findAllByTitle(String title);

    List<Event> findAllByDate(Date day);

    @NonNull
    List<Event> findAll();
}
