package com.lcwd.user.service.externalclients;

import com.lcwd.user.service.entities.Ratings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "RATING-SERVICES")
public interface RatingService {

    @GetMapping("/rating-service/userId/{userId}")
    Ratings[] getRatingsOfUser(@PathVariable("userId") String userId);

    @GetMapping("/rating-service/getAll-rating")
    Ratings[] getRatingsOfAllUsers();

    /*
    Calling Post, PUT and DELETE Rating apis through feign client
     */

    //To Give rating
    @PostMapping("/rating-service/give-rating")
    ResponseEntity<Ratings> createRating(@RequestBody Ratings ratings);

    //To update the rating
    @PutMapping("/rating-service/updateRating/{ratingId}")
    ResponseEntity<Ratings> updateRating(@PathVariable("ratingId") String ratingId,Ratings ratings);

    //To delete ratings
    @DeleteMapping("/rating-service/deleteRating/{ratingId}")
    ResponseEntity<String> deleteRating(@PathVariable("ratingId")String ratingId);


}
