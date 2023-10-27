package com.userservice.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userservice.entities.Hotel;
import com.userservice.entities.Rating;
import com.userservice.entities.User;
import com.userservice.exceptions.ResourceNotFoundException;
import com.userservice.external.services.HotelService;
import com.userservice.repositories.UserRepository;
import com.userservice.services.UserService;

@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

	@Override
	public User saveUser(User user) {
		//generating user random id
		String randomId = UUID.randomUUID().toString();
		user.setId(randomId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		//fetch ratings of all user Rating Service
		return userRepository.findAll();
	}

	@Override
	public User getUser(String id) {
		//fetch User from DataBase
		User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found of id " + id));
		//fetch ratings of user from Rating Service
		//http://localhost:8083/ratings/users/{userID}
		Rating[] ratingsByUser = restTemplate.getForObject("http://ratingservice/ratings/users/"+id, Rating[].class);
		
		List<Rating> ratings = Arrays.asList(ratingsByUser).stream().map(rating->{
			//fetch all hotels from each rating
			//http://localhost:8082/hotels/{hotelID}
//			ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://hotelservice/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotelByRating = hotelEntity.getBody();
			Hotel hotelByRating = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotelByRating);
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratings);
		return user;
	}

}
