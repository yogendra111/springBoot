package com.ratingservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingservice.entities.Rating;
import com.ratingservice.services.RatingService;

@RestController
@RequestMapping("ratings")
public class RatingController {
	
	@Autowired
	RatingService ratingService;

	//create
	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ratingService.create(rating));
	}
	
	//find single Rating
	@GetMapping("/{ratingId}")
	public ResponseEntity<Rating> getRating(@PathVariable("ratingId") String id){
		return ResponseEntity.ok(ratingService.get(id));
	}
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
		return ResponseEntity.ok(ratingService.getAllByUserId(userId));
	}
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
		return ResponseEntity.ok(ratingService.getAllByHotelId(hotelId));
	}
	
	//findAll Rating
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRating(){
		return ResponseEntity.ok(ratingService.getAll());
	}
	
}
