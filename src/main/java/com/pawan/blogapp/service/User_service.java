package com.pawan.blogapp.service;

import java.util.List;

import com.pawan.blogapp.payloads.UserDto;

public interface User_service  {

	UserDto createUser(UserDto user);
		
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
	
}
