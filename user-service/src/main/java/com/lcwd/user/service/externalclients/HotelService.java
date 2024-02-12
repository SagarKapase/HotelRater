package com.lcwd.user.service.externalclients;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Ratings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    //byDefault method is public in interface

    //calling hotel service
    @GetMapping("/hotel-service/getSingleHotel/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") String hotelId);


}
