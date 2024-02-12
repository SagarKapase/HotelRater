package com.lcwd.user.service;

import com.lcwd.user.service.entities.Ratings;
import com.lcwd.user.service.externalclients.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;
	@Test
	void contextLoads() {
	}

	@Test
	void deleteRating(){
		ratingService.deleteRating("65069f3a0018ef3cac55b67c");
		System.out.println("Rating deleted successfully...");
	}

	@Test
	void createRating(){
		Ratings ratings=Ratings.builder()
				.rating(555)
				.userId("3335f74c-d546-49ef-bbf4-805a74a6dd93")
				.hotelId("5543823d-40a3-4a74-8943-aa31a4729f38")
				.feedback("Tasty").build();
		Ratings saveRatings= ratingService.createRating(ratings).getBody();
		System.out.println("Rating created successfully....");

	}

}
