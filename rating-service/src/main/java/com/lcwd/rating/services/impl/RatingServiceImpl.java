package com.lcwd.rating.services.impl;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.exception.ResourceNotFoundException;
import com.lcwd.rating.repository.RatingRepository;
import com.lcwd.rating.services.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingServices {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public Rating updateRatings(String ratingId, Rating rating) {
        Rating rating_id = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("ID not found"));
        rating_id.setRatingId(rating.getRatingId());
        rating_id.setUserId(rating.getUserId());
        rating_id.setHotelId(rating.getHotelId());
        rating_id.setRating(rating.getRating());
        rating_id.setFeedback(rating.getFeedback());

        Rating updatedRating = ratingRepository.save(rating_id);
        return updatedRating;
    }

    @Override
    public String deleteRating(String ratingId) {
        Rating rating_id = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("ID not found"));
        ratingRepository.deleteById(rating_id.getRatingId());

        return "Rating deleted successfully....";
    }
}
