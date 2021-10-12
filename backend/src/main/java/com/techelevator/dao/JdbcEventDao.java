package com.techelevator.dao;

import com.techelevator.model.Event;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcEventDao implements EventDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcEventDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Event> getUpcomingEvents(){
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events e JOIN breweries b ON e.brewery_id=b.brewery_id " +
                "WHERE event_date >= CURRENT_DATE ORDER BY event_date, event_start_time;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            events.add(mapRowToEvent(result));
        }
        return events;
    }

    @Override
    public List<Event> getUpcomingBreweryEvents(int id){
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events e JOIN breweries b ON e.brewery_id=b.brewery_id " +
                "WHERE event_date >= CURRENT_DATE AND e.brewery_id = ? ORDER BY event_date, event_start_time;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        while(result.next()){
            events.add(mapRowToEvent(result));
        }
        return events;
    }

    @Override
    public long createEvent(Event e){
        String sql = "INSERT INTO events (event_name, event_description, brewery_id, event_date, event_start_time, event_end_time) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING event_id;";
            long newId = jdbcTemplate.queryForObject(sql, Long.class, e.getName(), e.getDescription(), e.getBreweryId(),
                    e.getDate(), e.getStartTime(), e.getEndTime());
            return newId;

    }

    @Override
    public Event getEvent(long id){
        String sql = "SELECT * FROM events e JOIN breweries b ON b.brewery_id = e.brewery_id WHERE event_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        result.next();
        return mapRowToEvent(result);
    }

    @Override
    public void updateEvent(Event e){
        String sql = "UPDATE events SET event_name=?, event_description=?, event_date=?, " +
                "event_start_time=?, event_end_time=? WHERE event_id = ?;";
        jdbcTemplate.update(sql, e.getName(), e.getDescription(), e.getDate(), e.getStartTime(), e.getEndTime(), e.getId());
    }

    @Override
    public void deleteEvent(long id){
        String sql = "DELETE FROM events WHERE event_id = ?;";
        jdbcTemplate.update(sql, id);
    }



    private Event mapRowToEvent(SqlRowSet r){
        Event e = new Event();
        e.setId(r.getLong("event_id"));
        e.setName(r.getString("event_name"));
        e.setBreweryId(r.getLong("brewery_id"));
        e.setDescription(r.getString("event_description"));
        e.setLocation(r.getString("address") + ":" + r.getString("city") + ", " + r.getString("state_abrev") + " " + r.getString("zip"));
        e.setBreweryName(r.getString("brewery_name"));
        Date date = r.getDate("event_date");
        Time startTime = r.getTime("event_start_time");
        Time endTime = r.getTime("event_end_time");
        e.setStartTime(null);
        e.setDate(null);
        e.setEndTime(null);
        if (date != null){
            e.setDate(r.getDate("event_date").toLocalDate());
        }
        if (startTime != null){
            e.setStartTime(r.getTime("event_start_time").toLocalTime());
        }
        if (endTime != null){
            e.setEndTime(r.getTime("event_end_time").toLocalTime());
        }
        return e;
    }
}
