package com.example.user.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.model.ErrorResponseModel;
import com.example.user.model.UserRequestModel;
import com.example.user.model.UserResponseModel;
import com.example.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userServiceImpl;

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody UserRequestModel userRequestModel) {
		UserResponseModel userResponseModel = userServiceImpl.saveUserDetails(userRequestModel);
		ResponseEntity<UserResponseModel> response = new ResponseEntity<>(userResponseModel, HttpStatus.CREATED);
		return response;
	}

	@PostMapping(value = "/multiple", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<UserResponseModel> createMultipleUser(@RequestBody List<UserRequestModel> listUserRequestModel) {

		List<UserResponseModel> listUserResponseModel = userServiceImpl.saveUserDetails(listUserRequestModel);

		return listUserResponseModel;
	}

	@GetMapping
	public List<UserResponseModel> getAllUsers() {
		return userServiceImpl.getAllUsers();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserResponseModel> getUserByUserId(@PathVariable Integer userId) {
		UserResponseModel userResponseModel = userServiceImpl.getUserByUserId(userId);
		ResponseEntity<UserResponseModel> response = new ResponseEntity<>(userResponseModel, HttpStatus.CREATED);
		return response;
	}

	@GetMapping("/firstName/{firstName}")
	public List<UserResponseModel> getUserByFirstName(@PathVariable String firstName) {
		return userServiceImpl.getUserByFirstName(firstName);

	}

	@GetMapping("/firstNameAndCity")
	public List<UserResponseModel> getUserByFirstNameAndCity(@RequestParam(required = true) String firstName,
			@RequestParam(required = true) String city) {
		return userServiceImpl.getUserByFirstNameAndCity(firstName, city);

	}

	@GetMapping("/firstNameAndLastName")
	public List<UserResponseModel> getUserByFirstNameAndCity(@RequestParam(required = true) String firstName,
			@RequestParam(required = true) String lastName, @RequestParam(required = true) String operation) {
		return userServiceImpl.findByFirstNameLastName(firstName, lastName, operation);

	}

	@PutMapping(value = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> updateUser(@RequestBody UserRequestModel userRequestModel,
			@PathVariable Integer userId) {
		userServiceImpl.updateMobileNumberForUserId(userId, userRequestModel.getMobileNumber());
		ResponseEntity<String> response = new ResponseEntity<>("User Updated Successfully", HttpStatus.CREATED);
		return response;
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> DeleteUser(@PathVariable Integer userId) {
		userServiceImpl.deleteUser(userId);
		ResponseEntity<String> response = new ResponseEntity<>("User deleted Successfully", HttpStatus.OK);
		return response;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponseModel handleValidationExceptions(MethodArgumentNotValidException ex) {
		ErrorResponseModel errorResponseModel = null;

		for (ObjectError errorObject : ex.getBindingResult().getAllErrors()) {

			errorResponseModel = new ErrorResponseModel(new Date(), errorObject.getDefaultMessage(), "ERROR001");
		}

		return errorResponseModel;
	}

}
