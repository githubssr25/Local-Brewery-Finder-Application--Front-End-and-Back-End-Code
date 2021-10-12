package com.techelevator.dao;

import java.util.List;
import com.techelevator.model.Beer;

public interface BeerDao {
    List<Beer> getBeers();

    Beer getBeerById(int breweryId, int id);

    long addBeer(Beer beerToInsert);

    void deleteBeer(long id);

    void deleteBeersByBrewery(int breweryId);

    List<Beer> getBeersByBreweryId(int breweryId);

    void updateBeer(Beer beerToUpdate);

}
