package com.user.controller;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.user.entity.User;
import com.user.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	@Test
	void testCreateUser() {

		// Prepare mock data
		User user = new User();
		user.setUserId(1);
		user.setName("John");
		user.setEmail("john@example.com");

		// Mock the UserService's saveUser method
		Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(user);

		// Call the UserController's saveUser method
		ResponseEntity<User> result = userController.createUser(user);

		// Assert the result
		Assert.assertNotNull(result);
		Assert.assertEquals(HttpStatus.CREATED, result.getStatusCode());
		Assert.assertEquals(user, result.getBody());

		// Verify that the UserService's saveUser method was called with the correct
		// user object
		Mockito.verify(userService, Mockito.times(1)).saveUser(Mockito.any(User.class));

	}

	@Test
	void testGetSingleUser() {
		User user = new User();
		user.setUserId(1);
		user.setName("John");
		user.setEmail("john@example.com");

		// Mock the UserService's findUserById method
		Mockito.when(userService.getUser(Mockito.anyString())).thenReturn(user);

		// Call the UserController's findUserById method
		ResponseEntity<User> result = userController.getSingleUser("1");

		// Assert the result
		Assert.assertNotNull(result);
		Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assert.assertEquals(user, result.getBody());

		// Verify that the UserService's findUserById method was called with the correct
		// user ID
		Mockito.verify(userService, Mockito.times(1)).getUser(Mockito.anyString());
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

		// Mock the UserService's getAllUsers method
		Mockito.when(userService.getAllUser()).thenReturn(userList);

		// Call the UserController's getAllUsers method
		ResponseEntity<List<User>> result = userController.getAllUser();

		// Assert the result
		Assert.assertNotNull(result);
		Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assert.assertEquals(userList, result.getBody());

		// Verify that the UserService's getAllUsers method was called once
		Mockito.verify(userService, Mockito.times(1)).getAllUser();

	}

}
