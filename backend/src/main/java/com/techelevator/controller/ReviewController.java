package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.ReviewDao;
import com.techelevator.model.Beer;
import com.techelevator.model.Review;

@RestController
@CrossOrigin
public class ReviewController {

    @Autowired
    ReviewDao reviewDao;

    @Autowired
    UserDao userDao;

    @GetMapping(path="/breweries/{breweryId}/reviews")
    public List<Review> getBreweryReviews(@PathVariable int breweryId){
        return reviewDao.getAllReviewsByBreweryId(breweryId);
    }

    @GetMapping(path="/account/reviews")
    @PreAuthorize("isAuthenticated()")
    public List<Review> getUserReviews(Principal principal){
        return reviewDao.getAllReviewsByUserId(getId(principal));
    }

    @GetMapping(path="/breweries/{breweryId}/beers/{beerId}/reviews")
    public List<Review> getBeerReviews(@PathVariable int beerId){
        return reviewDao.getAllReviewsByBeerId(beerId);
    }

    @PutMapping(path="/editReview/{reviewId}")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public void updateReview(@RequestBody Review review) {
        reviewDao.updateReview(review);
    }

    @DeleteMapping(path="/account/reviews")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReviews(@PathVariable int userId) {
        reviewDao.deleteReviews(userId);
    }

    @PostMapping(path="/breweries/{breweryId}/reviews")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@RequestBody Review r, Principal principal) {
        r.setUserId(getId(principal));
        reviewDao.createReview(r);
    }

    @PostMapping(path="/breweries/{breweryId}/beers/{beerId}/reviews")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBeerReview(@RequestBody Review r, Principal principal) {
        r.setUserId(getId(principal));
        reviewDao.createReview(r);
    }

    private long getId(Principal principal){
        return userDao.findIdByUsername(principal.getName());
    }









}
