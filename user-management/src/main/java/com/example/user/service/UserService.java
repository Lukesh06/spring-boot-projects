package com.example.user.service;

import java.util.List;

import com.example.user.model.UserRequestModel;
import com.example.user.model.UserResponseModel;

public interface UserService {

	UserResponseModel populateResponse(UserRequestModel userRequestModel);

	UserResponseModel saveUserDetails(UserRequestModel userRequestModel);

	List<UserResponseModel> saveUserDetails(List<UserRequestModel> listUserRequestModel);

	List<UserResponseModel> getAllUsers();

	UserResponseModel getUserByUserId(Integer userId);

	List<UserResponseModel> getUserByFirstName(String firstName);

	List<UserResponseModel> getUserByFirstNameAndCity(String firstName, String city);

	List<UserResponseModel> findByFirstNameLastName(String firstName, String lastName, String operation);
	
	void updateMobileNumberForUserId(int userId, String mobileNumber);
	
	void deleteUser(int userId);
	

}
