package com.techelevator.dao;

import com.techelevator.model.Brewery;
import com.techelevator.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.model.Beer;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcBreweryDao implements BreweryDao{

    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private BeerDao beerDao;

    private JdbcTemplate jdbcTemplate;

    public JdbcBreweryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Brewery> getAllBreweries() {
        List <Brewery> breweryList = new ArrayList<>();
        String sql = "SELECT * FROM breweries JOIN food ON food.food_id = breweries.food_served ORDER BY brewery_name"; //order by for sorting order by breweries
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            Brewery brewery = mapRowSetToBrewery(result);
            breweryList.add(brewery);
        }
        return breweryList;
    }

    @Override
    public Brewery getBreweryByIdLogged(int id, long userId) {
        String sqlGetBrewery = "SELECT * FROM breweries b JOIN user_breweries ub ON b.brewery_id = ub.brewery_id " +
                "JOIN food f ON f.food_id = b.food_served WHERE b.brewery_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetBrewery, id);
        result.next();
        Brewery brewery = mapRowSetToBrewery(result);
        if (result.getLong("user_id") == userId){
            brewery.setOwner(true);
        }else{
            brewery.setOwner(false);
        }
        return brewery;
    }

    @Override
    public Brewery getBreweryById(int id) {
        String sqlGetBrewery = "SELECT * FROM breweries b JOIN user_breweries ub ON b.brewery_id = ub.brewery_id " +
                "JOIN food f ON f.food_id = b.food_served WHERE b.brewery_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetBrewery, id);
        result.next();
        Brewery brewery = mapRowSetToBrewery(result);
        brewery.setOwner(false);
        return brewery;
    }



    @Override
    public List<Brewery> getBreweryByUserId(long id){
        List<Brewery> breweries = new ArrayList<>();
        String sql = "SELECT * FROM breweries " +
                "JOIN user_breweries ON breweries.brewery_id = user_breweries.brewery_id " +
                "JOIN food ON food.food_id = breweries.food_served " +
                "WHERE user_breweries.user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        while(result.next()){
            breweries.add(mapRowSetToBrewery(result));
        }
        return breweries;
    }
    @Override
    public long createBrewery(Brewery newBrewery) {
        String sql = "INSERT INTO breweries(brewery_name, contact_info, brewery_history, operation_time, address, " +
                "city, state_abrev, zip, website, image, active, food_served) VALUES (?,?,?,?,?,?,?,?,?,?,?, ?) RETURNING brewery_id";
        long newId =
                jdbcTemplate.queryForObject(sql, Long.class, newBrewery.getName(),newBrewery.getContactInfo(),
                        newBrewery.getHistory(), newBrewery.getOperationTime(), newBrewery.getAddress(),
                        newBrewery.getCity(), newBrewery.getState(), newBrewery.getZipCode(), newBrewery.getWebsite(),
                newBrewery.getImage(), newBrewery.isActive(), newBrewery.getFoodId());
        sql = "INSERT INTO user_breweries (user_id, brewery_id) VALUES (?, ?);";
        jdbcTemplate.update(sql, newBrewery.getOwnerId(), newId);
        return newId;
    }

    @Override
    public void deleteBrewery(int id) {
        beerDao.deleteBeersByBrewery(id);
        reviewDao.deleteBreweryReviews(id);
        String sql = "DELETE FROM user_breweries WHERE brewery_id = ?;";
        jdbcTemplate.update(sql, id);
        sql = "DELETE FROM events WHERE brewery_id = ?;";
        jdbcTemplate.update(sql, id);
        sql = "DELETE FROM breweries WHERE brewery_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateBrewery(Brewery oneBrewery) {
        String sql = "UPDATE breweries SET brewery_name =?, contact_info =?, brewery_history =?, operation_time =?, " +
                "address =?, city =?, state_abrev =?, zip =?, website =?, image =?, active=?, food_served =? WHERE brewery_id = ?";
        jdbcTemplate.update(sql, oneBrewery.getName(),oneBrewery.getContactInfo(), oneBrewery.getHistory(),
                oneBrewery.getOperationTime(), oneBrewery.getAddress(), oneBrewery.getCity(), oneBrewery.getState(),
                oneBrewery.getZipCode(), oneBrewery.getWebsite(), oneBrewery.getImage(), oneBrewery.isActive(),
                oneBrewery.getFoodId(), oneBrewery.getId());
    }

    @Override
    public List<Food> getFoodList(){
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM food;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            Food food = new Food(result.getLong("food_id"), result.getString("food_name"));
            foods.add(food);
        }
        return foods;
    }

    @Override
    public void updateBrewer(Brewery brewery){
        String sql = "UPDATE user_breweries SET user_id = ? WHERE brewery_id = ?";
        jdbcTemplate.update(sql, brewery.getOwnerId(), brewery.getId());
    }

    private Brewery mapRowSetToBrewery(SqlRowSet result) {
        Brewery brewery = new Brewery();
        brewery.setId(result.getInt("brewery_id"));
        brewery.setActive(result.getBoolean("active"));
        brewery.setCity(result.getString("city"));
        brewery.setHistory(result.getString("brewery_history"));
        brewery.setContactInfo(result.getString("contact_info"));
        brewery.setName(result.getString("brewery_name"));
        brewery.setState(result.getString("state_abrev"));
        brewery.setAddress(result.getString("address"));
        brewery.setWebsite(result.getString("website"));
        brewery.setZipCode(result.getString("zip"));
        brewery.setImage(result.getString("image"));
        brewery.setOperationTime(result.getString("operation_time"));
        brewery.setFood(result.getString("food_name"));
        brewery.setFoodId(result.getLong("food_served"));
        return brewery;
    }

    /*private Food mapRowSetToFood(SqlRowSet result){
        Food food = new Food();
        food.setId(result.getLong("food_id"));
        food.setName(result.getString("food_name"));
        return food;
    }*/
}