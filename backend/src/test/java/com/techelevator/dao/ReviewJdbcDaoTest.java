package com.techelevator.dao;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.model.Review;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.techelevator.model.Review;

import java.util.List;

public class ReviewJdbcDaoTest extends FinalCapstoneDaoTests {

private static final Review REVIEW_1 = new Review (1, "Brandon Czekalski", "I love this place", "Hitchhiker is a really cool brewery.", 4, "Brewery", 4, 9);
private static final Review REVIEW_2 = new Review (2, "Brandon Czekalski", "This place is okay", "Eleventh Hour has really interesting beers, but the atmosphere in the tap room sucks.", 3, "Brewery", 4, 1);
private static final Review REVIEW_4 = new Review (4, "Celeste", "Worst service ever", "Church Brew sucks.", 1, "Brewery", 5, 6);
private static final Review REVIEW_5 = new Review (5, "Brandon Czekalski", "Do not recommend", "Cosmic Void tastes okay.", 2, "Beer", 4, 3);

private static final Beer BEER_1= new Beer(9, "Southern Tier Nu Haze", "IPA", "Citrus and ripe melon notes in this hazy IPA", 6.0F,
            "https://churchbrew.com/wp-content/themes/yeast/img/logo.png", false, 3);
private static final Beer BEER_2 = new Beer(8, "Tropical Seltzer", "Seltzer", "A crisp mango infused seltzer.  One hundred calories of refreshment!", 4.8F,
            "https://churchbrew.com/wp-content/themes/yeast/img/logo.png", false, 6);
private static final Beer BEER_3 = new Beer(1, "Cosmic Void", "IPA", "Brewed and dry hopped with Sabro. Notes of Pineapple, Coconut, Tropical Fruit, and Cedar.", 7.0F,
            "https://hitchhiker.beer/wp-content/uploads/HHBC_CosmicVoid_bc_01102019_CMYK.jpg", false, 9);
private static final Beer BEER_4 = new Beer(3, "Fruit Rush- Raspberry", "Smoothie Sour Shandy", "Brewed with Oats and Wheat. Conditioned on Lemon and Raspberry.", 4.8F,
            "https://hitchhiker.beer/wp-content/uploads/HHBC_FruitRushRaspberry_bc_49375x7-1200x757.jpg", false, 1);

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

private JdbcTemplate template;
private JdbcReviewDao reviewDao;
private JdbcBreweryDao breweryDao;
private JdbcBeerDao beerDao;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        template = new JdbcTemplate(dataSource);
        reviewDao = new JdbcReviewDao(template);

    }

    @Test
    public void getAllReviewsByBreweryId(){
        List<Review> listToTest = reviewDao.getAllReviewsByBreweryId(REVIEW_1.getTargetId());
        assertReviewsMatch(REVIEW_1, listToTest.get(0));
        List<Review> listToTest2 = reviewDao.getAllReviewsByBreweryId(REVIEW_2.getTargetId());
        assertReviewsMatch( REVIEW_2, listToTest.get(0));
        List<Review> listToTest4 = reviewDao.getAllReviewsByBreweryId(REVIEW_4.getTargetId());
        assertReviewsMatch( REVIEW_4, listToTest.get(0));
        List<Review> listToTest5 = reviewDao.getAllReviewsByBreweryId(REVIEW_5.getTargetId());
        assertReviewsMatch( REVIEW_5, listToTest.get(0));

    }

@Test
    public void getAllReviewsByBeerId(){
        List<Review> listToTest = reviewDao.getAllReviewsByBeerId(BEER_1.getBreweryId());
        assertReviewsMatch(REVIEW_1, listToTest.get(0));
        List<Review> listToTest2 = reviewDao.getAllReviewsByBeerId(BEER_4.getBreweryId());
        assertReviewsMatch( REVIEW_2, listToTest.get(0));
        List<Review> listToTest4 = reviewDao.getAllReviewsByBeerId(BEER_2.getBreweryId());
        assertReviewsMatch( REVIEW_4, listToTest.get(0));
        List<Review> listToTest5 = reviewDao.getAllReviewsByBeerId(BEER_3.getBreweryId());
        assertReviewsMatch( REVIEW_5, listToTest.get(0));

    }


    @Test
    public void updateReviewExpectedValue () {

        Review reviewToUpdate = reviewDao.getReviewByReviewId(1);
        reviewToUpdate.setName("updated");
        reviewToUpdate.setTitle("updated title");
        reviewToUpdate.setText("updated review text");
        reviewToUpdate.setStarCount(3);
        reviewToUpdate.setType("review brewery update");
        reviewToUpdate.setUserId(4);
        reviewToUpdate.setTargetId(3);

        reviewDao.updateReview(reviewToUpdate);

        Review retrievedUpdate = reviewDao.getReviewByReviewId(1);
        assertReviewsMatch(reviewToUpdate, retrievedUpdate);

    }

    @Test
    public void deletedReviewCantRetrieve() {
        reviewDao.deleteReview(1);

        Review retrievedReview = reviewDao.getReviewByReviewId(1);
        Assert.assertNull(retrievedReview);

        List<Review> reviews1 = reviewDao.getAllReviewsByBreweryId(9);
        Assert.assertEquals(1, reviews1.size());
        assertReviewsMatch(REVIEW_1, reviews1.get(0));
    }


    private void assertReviewsMatch(Review expected, Review actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getTitle(), actual.getTitle());
        Assert.assertEquals(expected.getText(), actual.getText());
        Assert.assertEquals(expected.getStarCount(), actual.getStarCount());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getTargetId(), actual.getTargetId());

    }






















}
