package com.user.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.user.entity.User;
import com.user.repository.UserInterface;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserInterface userRepository;

	@Test
	void testSaveUser() {
		// Prepare mock data
		User user = new User();
		user.setName("John");
		user.setEmail("john@example.com");

		// Mock the UserRepository's save method
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

		// Call the UserService's saveUser method
		User savedUser = userService.saveUser(user);

		// Assert the result
		Assert.assertNotNull(savedUser);
		Assert.assertEquals(user.getName(), savedUser.getName());
		Assert.assertEquals(user.getEmail(), savedUser.getEmail());

		// Verify that the UserRepository's save method was called once
		Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
	}

	@Test
	void testGetAllUser() {
		// Prepare mock data
		User user1 = new User();
		user1.setUserId(1);
		user1.setName("John");
		user1.setEmail("john@example.com");

		User user2 = new User();
		user2.setUserId(2);
		user2.setName("Jane");
		user2.setEmail("jane@example.com");

		List<User> userList = Arrays.asList(user1, user2);

		// Mock the UserRepository's findAll method
		Mockito.when(userRepository.findAll()).thenReturn(userList);

		// Call the UserService's getAllUsers method
		List<User> result = userService.getAllUser();

		// Assert the result
		Assert.assertNotNull(result);
		Assert.assertEquals(userList.size(), result.size());
		Assert.assertEquals(userList.get(0).getName(), result.get(0).getName());
		Assert.assertEquals(userList.get(1).getName(), result.get(1).getName());

		// Verify that the UserRepository's findAll method was called once
		Mockito.verify(userRepository, Mockito.times(1)).findAll();
	}

}
