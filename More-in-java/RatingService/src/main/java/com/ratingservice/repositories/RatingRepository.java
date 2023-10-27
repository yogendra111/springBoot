package com.ratingservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ratingservice.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>{
	//write custom method here...
	List<Rating> findByHotelId(String hotelId);
	List<Rating> findByUserId(String userId);
}
