package com.techelevator.dao;

import java.util.List;
import com.techelevator.model.Brewery;
import com.techelevator.model.Food;

public interface BreweryDao {

    List<Brewery> getAllBreweries();

    Brewery getBreweryByIdLogged(int id, long userId);

    Brewery getBreweryById(int id);

    List<Brewery> getBreweryByUserId(long id);

    long createBrewery(Brewery newBrewery);

    void deleteBrewery(int id);

    void updateBrewery(Brewery aBrewery);

    List<Food> getFoodList();

    void updateBrewer(Brewery brewery);
}