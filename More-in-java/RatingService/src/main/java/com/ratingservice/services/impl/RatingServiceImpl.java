package com.ratingservice.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratingservice.entities.Rating;
import com.ratingservice.exceptions.ResourceNotFoundException;
import com.ratingservice.repositories.RatingRepository;
import com.ratingservice.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	RatingRepository ratingRepository;

	@Override
	public Rating create(Rating rating) {
		String randomId = UUID.randomUUID().toString();
		rating.setId(randomId);
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAll() {
		return ratingRepository.findAll();
	}

	@Override
	public Rating get(String id) {
		return ratingRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Rating of given id not found"));
	}

	@Override
	public List<Rating> getAllByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}

	@Override
	public List<Rating> getAllByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

}
