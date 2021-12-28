package com.epam.storage;

import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import com.epam.model.impl.EventImpl;
import com.epam.model.impl.TicketImpl;
import com.epam.model.impl.UserImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Component
public class Storage {

    private static final Log LOGGER = LogFactory.getLog(Storage.class);
    private final Map<Long, User> users;
    private final Map<Long, Event> events;
    private final Map<Long, Ticket> tickets;
    private String pathToData;

    public Storage() {
        users = new HashMap<>();
        events = new HashMap<>();
        tickets = new HashMap<>();
    }

    private void fillMapsWithDataFromFile() {
        try (FileReader fileReader = new FileReader(pathToData);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] cellOfStorage = line.split(", ");

                switch (cellOfStorage[0]) {
                    case "User":
                        LOGGER.info("inserting users in storage from file (data.txt)");
                        User user = new UserImpl(cellOfStorage[2], cellOfStorage[3]);
                        user.setId(Long.parseLong(cellOfStorage[1]));

                        users.put(user.getId(), user);
                        break;

                    case "Event":
                        LOGGER.info("inserting events in storage from file (data.txt)");
                        Event event = new EventImpl(cellOfStorage[2], new SimpleDateFormat("dd-MM-yyyy").parse(cellOfStorage[3]));
                        event.setId(Long.parseLong(cellOfStorage[1]));

                        events.put(event.getId(), event);
                        break;

                    case "Ticket":
                        LOGGER.info("inserting tickets in storage from file (data.txt)");
                        TicketImpl ticket = new TicketImpl(
                                Long.parseLong(cellOfStorage[2]),
                                Long.parseLong(cellOfStorage[3]),
                                Ticket.Category.valueOf(cellOfStorage[4]),
                                Integer.parseInt(cellOfStorage[5]));
                        ticket.setId(Long.parseLong(cellOfStorage[1]));

                        tickets.put(ticket.getId(), ticket);
                        break;

                    default:
                        LOGGER.error("There is some mistake while reading data from file");
                        break;
                }
            }


        } catch (IOException | ParseException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Map<Long, User> getUsers() {
        return users;
    }

    public Map<Long, Event> getEvents() {
        return events;
    }

    public Map<Long, Ticket> getTickets() {
        return tickets;
    }

    public void setPathToData(String pathToData) {
        this.pathToData = pathToData;
    }
}
