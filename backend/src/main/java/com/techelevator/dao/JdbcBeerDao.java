package com.techelevator.dao;

import com.techelevator.model.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcBeerDao implements BeerDao{

    @Autowired
    private ReviewDao reviewDao;
    private JdbcTemplate jdbcTemplate;

    public JdbcBeerDao (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Beer> getBeers() {
        List<Beer> beerList = new ArrayList<>();
        String sql = "SELECT * FROM beers;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            Beer beer = mapRowSetToBeer(result);
            beerList.add(beer);
        }
        return beerList;
    }

    @Override
    public List<Beer> getBeersByBreweryId(int breweryId) {
        List<Beer> beerListbyBrewery = new ArrayList<>();
        String sqlGetBeersByBrew = "SELECT * FROM beers WHERE brewery_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetBeersByBrew, breweryId);
        while(result.next()) {
            Beer beer = (mapRowSetToBeer(result));
            beerListbyBrewery.add(beer);
        }
        return beerListbyBrewery;
    }

    @Override
    public Beer getBeerById(int breweryId, int id) {
        String sql = "SELECT * FROM beers " +
                "WHERE beer_id = ? AND brewery_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id, breweryId);
        result.next();
        return mapRowSetToBeer(result);
    }

    @Override
    public long addBeer(Beer b) {
        String sql = "INSERT INTO beers (beer_name, beer_type, beer_description, abv, image, gluten_free, brewery_id)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)" +
                "RETURNING beer_id;";
        long newId = jdbcTemplate.queryForObject(sql, Long.class, b.getName(), b.getBeerType(), b.getDescription(),
                b.getAbv(), b.getImageLink(), b.isGlutenFree(), b.getBreweryId());
        return newId;
    }

    @Override
    public void deleteBeer(long id) {
        reviewDao.deleteBeerReviews(id);
        String sql = "DELETE FROM beers WHERE beer_id = ?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteBeersByBrewery(int breweryId) {
        List<Integer> beers = new ArrayList<>();
        String sql = "SELECT beer_id FROM beers WHERE brewery_id = ?;";
        SqlRowSet r = jdbcTemplate.queryForRowSet(sql, breweryId);
        while(r.next()){
            beers.add(r.getInt("beer_id"));
        }
        for(int b : beers){
            reviewDao.deleteBeerReviews(b);
        }
        sql = "DELETE FROM beers WHERE brewery_id = ?;";
        jdbcTemplate.update(sql, breweryId);
    }

    @Override
    public void updateBeer(Beer b) {
        String sql = "UPDATE beers " +
                "SET beer_name = ?, beer_type = ?, beer_description = ?, abv = ?, " +
                "image = ?, gluten_free = ?, brewery_id = ? " +
                "WHERE beer_id = ?;";
        jdbcTemplate.update(sql, b.getName(), b.getBeerType(), b.getDescription(),
                b.getAbv(), b.getImageLink(), b.isGlutenFree(), b.getBreweryId(), b.getId());
    }

    public Beer mapRowSetToBeer(SqlRowSet rs){
        Beer b = new Beer();
        b.setId(rs.getLong("beer_id"));
        b.setName(rs.getString("beer_name"));
        b.setBeerType(rs.getString("beer_type"));
        b.setDescription(rs.getString("beer_description"));
        b.setAbv(rs.getFloat("abv"));
        b.setImageLink(rs.getString("image"));
        b.setGlutenFree(rs.getBoolean("gluten_free"));
        b.setBreweryId(rs.getLong("brewery_id"));
        return b;
    }
}
