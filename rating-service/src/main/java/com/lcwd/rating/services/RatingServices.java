package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;

import java.util.List;

public interface RatingServices {

    //create
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getAllRatings();

    //get all ratings by particular user using userId

    List<Rating> getRatingByUserId(String userId);

    //get all ratings by particular hotel using hotelId

    List<Rating> getRatingByHotelId(String hotelId);

    //update rating

    Rating updateRatings(String ratingId,Rating rating);

    //delete rating by rating Id
    String deleteRating(String ratingId);
}
