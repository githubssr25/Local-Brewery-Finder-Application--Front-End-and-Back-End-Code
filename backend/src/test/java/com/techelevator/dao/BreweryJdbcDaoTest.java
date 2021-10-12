package com.techelevator.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import com.techelevator.model.Beer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;


import com.techelevator.model.Brewery;


public class BreweryJdbcDaoTest extends FinalCapstoneDaoTests{

    private JdbcBreweryDao breweryDao;
    private JdbcBeerDao beerDao;
    JdbcTemplate template;


private static final Brewery BREWERY_1 = new Brewery (1, "HitchHiker", "(412) 343-1950", "Our original pub (and our former brewery) is located in the heart of Mt. Lebanon.",
        "Mon: Closed, Tues: 4-10PM, Wed: 4-10PM, Thur: 4-10PM, Fri: 4-10PM, Sat: 12-10PM, Sun: 12-8PM", "1500 S Canal St #2541", "Pittsburgh", "PA", "15215", "https://hitchhiker.beer/",
        "https://hitchhiker.beer/wp-content/uploads/hbbc-social-share-image.png", true, 8);

private static final Brewery BREWERY_2 = new Brewery(2, "Eleventh Hour", "(412) 676-8034", "No History available.", "Mon: Closed, Tues: Closed, Wed: 5-11PM, Thur: 5-11PM, Fri: 5-11PM, Sat: 12-11PM, Sun: 1-7PM", "3711 Charlotte St", "Pittsburgh", "PA", "15201",
        "https://www.11thhourbrews.com/",
        "https://lh3.googleusercontent.com/Fg-N3e4p6PI3df8y3zL1H3jl-2KHdZFqnl0bIxRhLlSbW5nONMXhn4vAofvcnrJv6RoeCoPnjmwQOcV41oHUA8Oj0azFIQ=s750", true, 8);

private static final Brewery BREWERY_3 = new Brewery(3, "The Church Brew Works", "(412) 688-8200", "On the 6th of August in 1993.",
        "Mon: 4-10:15PM, Tues: 4-10:15PM, Wed: 4-10:15PM, Thur: 4-10:15PM, Fri: 12:30-11PM, Sat: 12-11PM, Sun: 12:30-9PM", "3525 Liberty Ave", "Pittsburgh", "PA", "15201",
        "https://churchbrew.com/", "https://churchbrew.com/wp-content/themes/yeast/img/logo.png", true, 7);

Brewery breweryTest = new Brewery (8, "The Brew Works", "(412) 687-8200", "On the 6th of August in 1992.",
        "Mon: 4-10:15PM, Tues: 4-10:15PM, Wed: 4-10:15PM, Thur: 4-10:15PM, Fri: 12:30-11PM, Sat: 12-11PM, Sun: 12:30-9PM", "3525 Liberty Ave", "Pittsburgh", "PA", "15201",
        "https://churchbrew.com/", "https://churchbrew.com/wp-content/themes/yeast/img/logo.png", true, 3);



    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        template = new JdbcTemplate(dataSource);
        breweryDao = new JdbcBreweryDao(template);
    }

        @Test
        public void getAllBreweries(){
            List<Brewery> listToTest = breweryDao.getAllBreweries();
            assertBreweriesMatch(listToTest.get(0), BREWERY_1);
            assertBreweriesMatch(listToTest.get(1), BREWERY_2);
            assertBreweriesMatch(listToTest.get(2), BREWERY_3);
        }

        @Test
        public void getBreweriesByIdTest() {
            Brewery actual = breweryDao.getBreweryById(2);
            assertBreweriesMatch(actual, BREWERY_2);

        }

        @Test
        public void deleteBreweryTest() {
            assertEquals(1, breweryDao.getBreweryById(1));
            breweryDao.deleteBrewery(1);
            assertEquals(1, breweryDao.getBreweryById(1));
        }

        @Test
        public void deleteBreweriesByUserId() {
            assertEquals(1, breweryDao.getBreweryByUserId(2).size());
            beerDao.deleteBeersByBrewery(2);
        }

    @Test
    public void updateBreweryExpectedValue () {

        Brewery breweryToUpdate = breweryDao.getBreweryById(1);
        breweryToUpdate.setId(1);
        breweryToUpdate.setName("update");
        breweryToUpdate.setContactInfo("updated ingo");
        breweryToUpdate.setHistory("updated history");
        breweryToUpdate.setOperationTime("updated time");
        breweryToUpdate.setAddress("updated address");
        breweryToUpdate.setCity("updated city");
        breweryToUpdate.setState("updated state");
        breweryToUpdate.setZipCode("updated zip");
        breweryToUpdate.setWebsite("updated site");
        breweryToUpdate.setImage("updated image");
        breweryToUpdate.isActive();
        breweryToUpdate.setFoodId(1);

        breweryDao.updateBrewery(breweryToUpdate);

        Brewery retrivedUpdate = breweryDao.getBreweryById(1);
        assertBreweriesMatch(breweryToUpdate, retrivedUpdate);
    }
        






    private void assertBreweriesMatch(Brewery expected, Brewery actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getContactInfo(), actual.getContactInfo());
        Assert.assertEquals(expected.getHistory(), actual.getHistory());
        Assert.assertEquals(expected.getOperationTime(), actual.getOperationTime());
        Assert.assertEquals(expected.getAddress(), actual.getAddress());
        Assert.assertEquals(expected.getCity(), actual.getCity());
        Assert.assertEquals(expected.getState(), actual.getState());
        Assert.assertEquals(expected.getZipCode(), actual.getZipCode());
        Assert.assertEquals(expected.getWebsite(), actual.getWebsite());
        Assert.assertEquals(expected.getImage(), actual.getImage());
        Assert.assertEquals(expected.isActive(), actual.isActive());
        Assert.assertEquals(expected.getFoodId(), actual.getFoodId());

    }



    }

