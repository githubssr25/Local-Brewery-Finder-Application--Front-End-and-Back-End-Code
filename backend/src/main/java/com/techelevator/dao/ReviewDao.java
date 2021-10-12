package com.techelevator.dao;

import com.techelevator.model.Beer;
import com.techelevator.model.Review;

import java.util.List;

public interface ReviewDao {

    List<Review> getAllReviewsByBreweryId(long targetId);

    List<Review> getAllReviewsByBeerId(long targetId);

    long createReview(Review newReview);

    Review getReviewByReviewId(int reviewId);

    void updateReview(Review review);

    void deleteReview(int reviewId);

    List<Review> getAllReviewsByUserId(long userId);

    void deleteBeerReviews(long id);

    void deleteBreweryReviews(long id);

    int deleteReviews(int userId);
    
}
