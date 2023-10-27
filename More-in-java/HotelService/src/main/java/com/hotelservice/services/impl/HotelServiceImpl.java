package com.hotelservice.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelservice.entities.Hotel;
import com.hotelservice.exceptions.ResourceNotFoundException;
import com.hotelservice.repositories.HotelRepository;
import com.hotelservice.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel create(Hotel hotel) {
		String randomId = UUID.randomUUID().toString();
		hotel.setId(randomId);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel get(String id) {
		return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Request Hotel not found of id " + id));
	}

}
