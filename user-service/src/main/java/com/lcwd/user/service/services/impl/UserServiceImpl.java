package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Ratings;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundEXception;
import com.lcwd.user.service.externalclients.HotelService;
import com.lcwd.user.service.externalclients.RatingService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {

        //generate unique user id

        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    //Getting all users from database

    @Override
    public List<User> getAllUser() {

        //Calling Ratings API
        //Ratings[] ratingsOfUser=restTemplate.getForObject("http://RATING-SERVICES/rating-service/getAll-rating",Ratings[].class);

        //calling a feign client of rating service
        Ratings[] ratingsOfAllUsers=ratingService.getRatingsOfAllUsers();


        //convert ratings array into list
        List<Ratings> ratingsList=Arrays.stream(ratingsOfAllUsers).toList();

        //logger.info("{} ",ratingsList);

        //Finding all users and store it in the List
        List<User> allUsers=userRepository.findAll();

        for (User user: allUsers){
            List<Ratings> userRatings=ratingsList.stream()
                    .filter(ratings -> ratings.getUserId().equals(user.getUserId()))
                    .collect(Collectors.toList());
            user.setRatingsArrayList(userRatings);
        }

        //Calling Hotel API
        //Calling getSingleHotel endpoint
        //http://localhost:8082/hotel-service/getSingleHotel/ce5f41ef-c3cd-440c-9e92-023c70d73e15

        for (Ratings ratings:ratingsList){
            //ResponseEntity<Hotel> hotelResponseEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel-service/getSingleHotel/"+ratings.getHotelId(), Hotel.class);

            //Calling hotelService method using feign client
            Hotel hotel=hotelService.getHotel(ratings.getHotelId());

            ratings.setHotel(hotel);
        }

        return allUsers;
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundEXception("User with give id is not found on server please check it and enter correctly " + userId));

        //fetching ratings of user from RATING-SERVICE

        //http://localhost:8083/rating-service/userId/078f4653-19c5-40ce-8e96-82c9f67bbd0a

        //Ratings[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICES/rating-service/userId/" + userId, Ratings[].class);


        //Calling feign client ratings service
        Ratings[] ratingsOfUser=ratingService.getRatingsOfUser(userId);


//        logger.info("{} ",response);

        //convert Ratings Array into List
        List<Ratings> ratings= Arrays.stream(ratingsOfUser).toList();

        //Fetching Hotel information which are rated by user

        List<Ratings> ratingsList = ratings.stream().map(rating -> {
            //api call to hotel services to get a each hotel
            //http://localhost:8082/hotel-service/getSingleHotel/ce5f41ef-c3cd-440c-9e92-023c70d73e15

            //ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotel-service/getSingleHotel/"+rating.getHotelId(), Hotel.class);

            //calling feign client hotel service
            Hotel hotel=hotelService.getHotel(rating.getHotelId());

            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating

            return rating;
        }).collect(Collectors.toList());

        user.setRatingsArrayList(ratingsList);

        return user;

    }

    //To update the user
    @Override
    public User updateUser(String userId,User user) {
        User getUser=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundEXception("User didn't exist"));
        getUser.setName(user.getName());
        getUser.setEmail(user.getEmail());
        getUser.setAbout(user.getAbout());

        User updatedUser = userRepository.save(getUser);
        return updatedUser;
    }

    //To delete the user by userId
    @Override
    public String deleteUserByUserId(String userId) {
        User findUser=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundEXception("User not found"));
        userRepository.deleteById(findUser.getUserId());
        return "User deleted successfully...";
    }
}
