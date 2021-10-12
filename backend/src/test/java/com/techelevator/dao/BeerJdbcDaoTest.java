package com.techelevator.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import com.techelevator.model.Brewery;
import com.techelevator.model.Review;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;


import com.techelevator.model.Beer;


public class BeerJdbcDaoTest extends FinalCapstoneDaoTests {

    private JdbcBreweryDao breweryDao;
    private JdbcBeerDao beerDao;
    JdbcTemplate template;

    private static final Beer BEER_1= new Beer(9, "Southern Tier Nu Haze", "IPA", "Citrus and ripe melon notes in this hazy IPA", 6.0F,
            "https://churchbrew.com/wp-content/themes/yeast/img/logo.png", false, 3);
    private static final Beer BEER_2 = new Beer(8, "Tropical Seltzer", "Seltzer", "A crisp mango infused seltzer.  One hundred calories of refreshment!", 4.8F,
            "https://churchbrew.com/wp-content/themes/yeast/img/logo.png", false, 3);
    private static final Beer BEER_3 = new Beer(1, "Cosmic Void", "IPA", "Brewed and dry hopped with Sabro. Notes of Pineapple, Coconut, Tropical Fruit, and Cedar.", 7.0F,
            "https://hitchhiker.beer/wp-content/uploads/HHBC_CosmicVoid_bc_01102019_CMYK.jpg", false, 1);
    private static final Beer BEER_4 = new Beer(3, "Fruit Rush- Raspberry", "Smoothie Sour Shandy", "Brewed with Oats and Wheat. Conditioned on Lemon and Raspberry.", 4.8F,
            "https://hitchhiker.beer/wp-content/uploads/HHBC_FruitRushRaspberry_bc_49375x7-1200x757.jpg", false, 8);

    private static final Brewery BREWERY_1 = new Brewery (1, "HitchHiker", "(412) 343-1950", "Our original pub (and our former brewery) is located in the heart of Mt. Lebanon.",
            "Mon: Closed, Tues: 4-10PM, Wed: 4-10PM, Thur: 4-10PM, Fri: 4-10PM, Sat: 12-10PM, Sun: 12-8PM", "1500 S Canal St #2541", "Pittsburgh", "PA", "15215", "https://hitchhiker.beer/",
            "https://hitchhiker.beer/wp-content/uploads/hbbc-social-share-image.png", true, 8);

    private static final Brewery BREWERY_2 = new Brewery(2, "Eleventh Hour", "(412) 676-8034", "No History available.", "Mon: Closed, Tues: Closed, Wed: 5-11PM, Thur: 5-11PM, Fri: 5-11PM, Sat: 12-11PM, Sun: 1-7PM", "3711 Charlotte St", "Pittsburgh", "PA", "15201",
            "https://www.11thhourbrews.com/",
            "https://lh3.googleusercontent.com/Fg-N3e4p6PI3df8y3zL1H3jl-2KHdZFqnl0bIxRhLlSbW5nONMXhn4vAofvcnrJv6RoeCoPnjmwQOcV41oHUA8Oj0azFIQ=s750", true, 8);

    private static final Brewery BREWERY_3 = new Brewery(3, "The Church Brew Works", "(412) 688-8200", "On the 6th of August in 1993.",
            "Mon: 4-10:15PM, Tues: 4-10:15PM, Wed: 4-10:15PM, Thur: 4-10:15PM, Fri: 12:30-11PM, Sat: 12-11PM, Sun: 12:30-9PM", "3525 Liberty Ave", "Pittsburgh", "PA", "15201",
            "https://churchbrew.com/", "https://churchbrew.com/wp-content/themes/yeast/img/logo.png", true, 7);


    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        template = new JdbcTemplate(dataSource);
        beerDao = new JdbcBeerDao(template);
        breweryDao = new JdbcBreweryDao(template);

        Beer beerTest = new Beer(8, "Southern Tier", "IPA", "Mango flavor", 5.0F, "https://churchbrew.com/wp-content/themes/yeast/img/logo.png", false, 3);

    }


    @Test
    public void getBeersByIdTest() {
        Beer actual = beerDao.getBeerById(8, 3);
        assertBeersMatch(actual, BEER_4);
        Beer actual2 = beerDao.getBeerById(1, 1);
        assertBeersMatch(actual2, BEER_3);
        Beer actual3 = beerDao.getBeerById(3, 8);
        assertBeersMatch(actual3, BEER_2);
        Beer actual4 = beerDao.getBeerById(3, 9);
        assertBeersMatch(actual4, BEER_1);

    }

    @Test
    public void deleteBeerTest() {
        assertEquals(9, beerDao.getBeers().size());
        beerDao.deleteBeer(8);
        assertEquals(8, beerDao.getBeers().size());
    }

    @Test
    public void deleteBeersByBreweryId() {
        assertEquals(3, beerDao.getBeersByBreweryId(2).size());
        beerDao.deleteBeersByBrewery(2);
        assertEquals(2, beerDao.getBeersByBreweryId(2).size());
    }

    @Test
    public void updateBeerExpectedValue () {

       Beer beerToUpdate = beerDao.getBeerById(8,3);
       beerToUpdate.setId(1);
       beerToUpdate.setName("update");
       beerToUpdate.setBeerType("updated beer type");
       beerToUpdate.setDescription("updated description");
       beerToUpdate.setAbv(4F);
       beerToUpdate.setImageLink("updated image link");
       beerToUpdate.isGlutenFree();
       beerToUpdate.setBreweryId(2);

       beerDao.updateBeer(beerToUpdate);

       Beer retrivedUpdate = beerDao.getBeerById(8,3);
       assertBeersMatch(beerToUpdate, retrivedUpdate);
    }


    private void assertBeersMatch(Beer expected, Beer actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBeerType(), actual.getBeerType());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getAbv(), actual.getAbv());
        Assert.assertEquals(expected.getImageLink(), actual.getImageLink());
        Assert.assertEquals(expected.isGlutenFree(), actual.isGlutenFree());
        Assert.assertEquals(expected.getBreweryId(), actual.getBreweryId());
    }
}










