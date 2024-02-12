package com.lcwd.hotel.services;

import com.lcwd.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {
    //create Hotel
    Hotel createHotel(Hotel hotel);

    //get all hotels
    List<Hotel> getAllHotels();

    //get hotel by hotelId

    Hotel getHotel(String id);

}
