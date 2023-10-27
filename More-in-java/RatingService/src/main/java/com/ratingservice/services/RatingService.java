package com.ratingservice.services;

import java.util.List;

import com.ratingservice.entities.Rating;

public interface RatingService {
	Rating create(Rating rating);
	List<Rating> getAll();
	Rating get(String id);
	List<Rating> getAllByHotelId(String hotelId);
	List<Rating> getAllByUserId(String userId);
}
