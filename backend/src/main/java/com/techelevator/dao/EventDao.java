package com.techelevator.dao;

import com.techelevator.model.Event;

import java.util.List;

public interface EventDao {

    List<Event> getUpcomingEvents();

    List<Event> getUpcomingBreweryEvents(int id);

    long createEvent(Event e);

    Event getEvent(long id);

    void updateEvent(Event e);

    void deleteEvent(long id);


}
