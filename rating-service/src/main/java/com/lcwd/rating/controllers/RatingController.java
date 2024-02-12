package com.lcwd.rating.controllers;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating-service")
public class RatingController {

    @Autowired
    private RatingServices ratingServices;

    //create rest api
    @PostMapping("/give-rating")
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingServices.create(rating));
    }

    @GetMapping("/getAll-rating")
    public ResponseEntity<List<Rating>> getAllRatings(){
        return ResponseEntity.status(HttpStatus.OK).body(ratingServices.getAllRatings());
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(ratingServices.getRatingByUserId(userId));
    }
    @GetMapping("/hotelId/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(ratingServices.getRatingByHotelId(hotelId));
    }

    //update the ratings
    @PutMapping("/updateRating/{ratingId}")
    public ResponseEntity<Rating> updateRatings(@PathVariable("ratingId")String ratingId,@RequestBody Rating rating){
        Rating updateRating = ratingServices.updateRatings(ratingId, rating);
        return ResponseEntity.ok(updateRating);
    }

    //delete ratings
    @DeleteMapping("/deleteRating/{ratingId}")
    public ResponseEntity<String> deleteRatingByRatingId(@PathVariable("ratingId") String ratingId){
        ratingServices.deleteRating(ratingId);
        return ResponseEntity.ok("Rating Deleted Successfully...");
    }
}
