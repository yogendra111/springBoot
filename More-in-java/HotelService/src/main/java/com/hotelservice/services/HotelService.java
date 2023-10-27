package com.hotelservice.services;

import java.util.List;

import com.hotelservice.entities.Hotel;

public interface HotelService {

	//create
	Hotel create(Hotel hotel);
	
	//getAll
	List<Hotel> getAll();
	
	//get Single Hotel
	Hotel get(String id);
	
}
