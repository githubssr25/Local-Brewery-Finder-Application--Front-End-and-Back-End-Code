package com.techelevator.controller;


import com.techelevator.dao.EventDao;
import com.techelevator.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EventController {

    @Autowired
    EventDao eventDao;

    @GetMapping(path="/events")
    public List<Event> getUpcomingEvents(){
        return eventDao.getUpcomingEvents();
    }

    @GetMapping(path="/breweries/{breweryId}/events")
    public List<Event> getUpcomingBreweryEvents(@PathVariable int breweryId){
        return eventDao.getUpcomingBreweryEvents(breweryId);
    }

    @PostMapping(path="/account/breweries/{breweryId}/events/add")
    @ResponseStatus(HttpStatus.CREATED)
    public long createEvent(@RequestBody Event e){
        return eventDao.createEvent(e);
    }

    @GetMapping(path="/account/breweries/{breweryId}/events/{eventId}")
    public Event getEvent(@PathVariable long eventId){
        return eventDao.getEvent(eventId);
    }

    @PutMapping(path="/account/breweries/{breweryId}/events/edit")
    @ResponseStatus(HttpStatus.OK)
    public void updateEvent(@RequestBody Event e){
        eventDao.updateEvent(e);
    }

    @DeleteMapping(path="/account/breweries/{breweryId}/events")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEvent(@RequestParam long id){
        eventDao.deleteEvent(id);
    }

}
