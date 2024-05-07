package com.pawan.blogapp.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.blogapp.entities.*;
import com.pawan.blogapp.payloads.UserDto;
import com.pawan.blogapp.repositories.User_Repo;
import com.pawan.blogapp.service.User_service;
import com.pawan.blogapp.exceptions.*;


@Service
public class UserServiceImpl implements User_service {

	@Autowired
	private User_Repo userrepo;

	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		User savedUser= this.userrepo.save(user);
		
		return this.UsertoDto(savedUser);
	}
  
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
			 
			User user = this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException ("user" , "id" , userId));

			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setPassword(userDto.getPassword());
			user.setAbout(userDto.getAbout());
		
			User updatedUser = this.userrepo.save(user);
			UserDto userDto1 = this.UsertoDto(updatedUser);
			return userDto1;
	}
	
	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
		return this.UsertoDto(user);
	}
	
	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userrepo.findAll();
		List<UserDto> userDto = users.stream().map(user->this.UsertoDto(user)).collect(Collectors.toList());
		return userDto;	
	}
	
	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
		this.userrepo.delete(user);
		
	}
	
	private User dtoToUser(UserDto userDto) {
		
		User user = this.modelmapper.map(userDto,User.class);  // alternate way to convert one object to another(here dto to entity)
		
		
		/*
		 * User user = new User(); user.setId(userDto.getId());
		 * user.setName(userDto.getName()); user.setEmail(userDto.getEmail());
		 * user.setAbout(userDto.getAbout()); user.setPassword(userDto.getPassowrd());
		 * 
		 */
		return user;
		
	}
	
		private UserDto UsertoDto(User user) {
		
		UserDto userDto = this.modelmapper.map(user, UserDto.class);
		
		
		/*
		 * UserDto userdto = new UserDto(); userdto.setId(user.getId());
		 * userdto.setName(user.getName()); userdto.setEmail(user.getEmail());
		 * userdto.setAbout(user.getAbout()); userdto.setPassowrd(user.getPassword());
		*
		 */ 
		 return userDto;
		 
	}


}
