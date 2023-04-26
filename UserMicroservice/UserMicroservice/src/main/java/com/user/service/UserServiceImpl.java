package com.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.entity.Rate;
import com.user.entity.User;
import com.user.exception.ResourceNotFoundException;
//import com.user.external.service.RateService;
import com.user.repository.UserInterface;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInterface userRepository;

//	@Autowired
//	private RestTemplate restTemplate;

//	@Autowired
//	private RateService rateService;

	@Override
	public User saveUser(User user) {

//		String randomUserId = UUID.randomUUID().toString();
//		user.setUserId(randomUserId);

		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {

//		System.out.println(userId);
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found" + userId));

		// fetch rating of the above user from Rating service

//		ArrayList<Rate> ratingOfUser = restTemplate
//				.getForObject("http://localhost:8083/rating/user/" + user.getUserId(), ArrayList.class);
//
//		user.setRatings(ratingOfUser);

		return user;
	}

}
