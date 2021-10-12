package com.techelevator.dao;

import com.techelevator.model.Review;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcReviewDao implements ReviewDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcReviewDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Review> getAllReviewsByBreweryId(long targetId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews r JOIN brewery_reviews br ON br.review_id = r.review_id " +
                "JOIN breweries b ON b.brewery_id = br.brewery_id WHERE br.brewery_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, targetId);
        while(result.next()){
            Review review = mapRowSetToBreweryReview(result);
            reviews.add(review);
        }
        return reviews;
    }

    @Override
    public List<Review> getAllReviewsByBeerId(long targetId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews r JOIN beer_reviews br ON br.review_id = r.review_id " +
                "JOIN beers b on b.beer_id = br.beer_id WHERE b.beer_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, targetId);
        while(result.next()){
            Review review = mapRowSetToBeerReview(result);
            reviews.add(review);
        }
        return reviews;
    }


    @Override
    public long createReview(Review r) {
        String sql = "INSERT INTO reviews (reviewer_name, review_title, review_text, review_stars, review_type, user_id, target_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING review_id;";
        long newId = jdbcTemplate.queryForObject(sql, Long.class, r.getName(), r.getTitle(), r.getText(),
                r.getStarCount(), r.getType(), r.getUserId(), r.getTargetId());
        if (r.getType().equals("Brewery")){
            sql = "INSERT INTO brewery_reviews (review_id, brewery_id) VALUES (?, ?);";
            jdbcTemplate.update(sql, newId, r.getTargetId());
            return newId;
        }
        sql = "INSERT INTO beer_reviews (review_id, beer_id) VALUES (?, ?);";
        jdbcTemplate.update(sql, newId, r.getTargetId());
        return newId;
    }
    @Override
    public Review getReviewByReviewId(int reviewId) {
        String sql = "SELECT * FROM reviews WHERE review_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, reviewId);
        if (result.next()){
            return mapRowSetToReview(result);
        }
        return null;
    }
    @Override
    public void updateReview(Review r) {
        String sql = "UPDATE reviews SET reviewer_name = ?, review_title = ?, review_text = ?, " +
                "review_stars = ?, review_type = ?, user_id = ?, target_id = ? " +
                "WHERE review_id = ?;";
        jdbcTemplate.update(sql, r.getName(), r.getTitle(), r.getText(), r.getStarCount(),
                r.getType(), r.getUserId(), r.getTargetId(), r.getId());
    }

    @Override
    public void deleteReview(int reviewId) {
        String sql = "DELETE * FROM reviews WHERE review_id = ?;";
        jdbcTemplate.update(sql, reviewId);
    }

    @Override
    public List<Review> getAllReviewsByUserId(long userId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        while(result.next()){
            reviews.add(mapRowSetToReview(result));
        }
        return reviews;
    }

    @Override
    public int deleteReviews(int userId) {
        String sql = "DELETE FROM reviews WHERE userId = ?;";
        int count = jdbcTemplate.update(sql, userId);
        return count;
    }

    @Override
    public void deleteBeerReviews(long id){
        List<Integer> reviews = new ArrayList<>();
        String sql = "SELECT review_id FROM beer_reviews WHERE beer_id = ?;";
        SqlRowSet r = jdbcTemplate.queryForRowSet(sql, id);
        while(r.next()){
            reviews.add((r.getInt("review_id")));
        }
        sql = "DELETE FROM beer_reviews WHERE beer_id = ?;";
        jdbcTemplate.update(sql, id);
        sql = "DELETE FROM reviews WHERE review_id = ?;";
        for (int i : reviews){
            jdbcTemplate.update(sql, i);
        }
    }

    @Override
    public void deleteBreweryReviews(long id){
        List<Integer> reviews = new ArrayList<>();
        String sql = "SELECT review_id FROM brewery_reviews WHERE brewery_id = ?;";
        SqlRowSet r = jdbcTemplate.queryForRowSet(sql, id);
        while(r.next()){
            reviews.add((r.getInt("review_id")));
        }
        sql = "DELETE FROM brewery_reviews WHERE brewery_id = ?;";
        jdbcTemplate.update(sql, id);
        sql = "DELETE FROM reviews WHERE review_id = ?;";
        for (int i : reviews){
            jdbcTemplate.update(sql, i);
        }
    }

    private Review mapRowSetToBreweryReview(SqlRowSet rs) {
        Review r = new Review();
        r.setId(rs.getLong("review_id"));
        r.setName(rs.getString("reviewer_name"));
        r.setTitle(rs.getString("review_title"));
        r.setText(rs.getString("review_text"));
        r.setStarCount(rs.getInt("review_stars"));
        r.setType(rs.getString("review_type"));
        r.setUserId(rs.getLong("user_id"));
        r.setTargetId(rs.getLong("target_id"));
        r.setTargetName(rs.getString("brewery_name"));
        return r;
    }

    private Review mapRowSetToBeerReview(SqlRowSet rs) {
        Review r = new Review();
        r.setId(rs.getLong("review_id"));
        r.setName(rs.getString("reviewer_name"));
        r.setTitle(rs.getString("review_title"));
        r.setText(rs.getString("review_text"));
        r.setStarCount(rs.getInt("review_stars"));
        r.setType(rs.getString("review_type"));
        r.setUserId(rs.getLong("user_id"));
        r.setTargetId(rs.getLong("target_id"));
        r.setTargetName(rs.getString("beer_name"));
        return r;
    }

    private Review mapRowSetToReview(SqlRowSet rs) {
        Review r = new Review();
        r.setId(rs.getLong("review_id"));
        r.setName(rs.getString("reviewer_name"));
        r.setTitle(rs.getString("review_title"));
        r.setText(rs.getString("review_text"));
        r.setStarCount(rs.getInt("review_stars"));
        r.setType(rs.getString("review_type"));
        r.setUserId(rs.getLong("user_id"));
        r.setTargetId(rs.getLong("target_id"));
        return r;
    }

    private String getTargetName(long id, String type){
        String sql;
        if(type.equals("Beer")){
            sql = "SELECT * FROM beers b JOIN beer_reviews br ON br.beer_id = b.beer_id " +
                    "WHERE br.review_id = ?;";
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            result.next();
            return result.getString("beer_name");
        }else if(type.equals("Brewery")){
            sql = "SELECT * FROM breweries b JOIN brewery_reviews br ON br.brewery_id = b.brewery_id " +
                    "WHERE br.review_id = ?;";
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            result.next();
            return result.getString("brewery_name");
        }else{
            return null;
        }
    }
}