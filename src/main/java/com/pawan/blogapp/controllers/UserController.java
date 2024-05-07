package com.pawan.blogapp.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.blogapp.payloads.Api_Response;
import com.pawan.blogapp.payloads.UserDto;
import com.pawan.blogapp.service.User_service;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private User_service user_service;
	
	// POST --- create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto createUserDto = this.user_service.createUser(userDto);
		return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
		
		}
	
	
	  // PUT ----- Update user
	  
	   @PutMapping("/{userId}") 
	   public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
		   
		   UserDto updatedUser = this.user_service.updateUser(userDto, userId);
		   return ResponseEntity.ok(updatedUser);
		  
	   }
	   
	   // DELETE ----- delete user
	   
	   
	  /* 
	   @DeleteMapping("/{userId}")
	   public  ResponseEntity<?> deleteUser(@PathVariable Integer userId){
		    this.deleteUser(userId);
		    return new ResponseEntity(Map.of("message", "user deletes successfully"), HttpStatus.OK);
		    */		
	   
	   
		    // alternate  to above return type.
		   //  we can create API response class for returning msg.
	   @DeleteMapping("/{userId}")
	   public ResponseEntity<Api_Response> deleteUser(@PathVariable Integer userId){
		  this.user_service.deleteUser(userId);
		  return new ResponseEntity<Api_Response>(new Api_Response("user deleted successfully",true),HttpStatus.OK);
	   
	   }
	   
	   // GET all user
	   
	   @GetMapping("/")
	   public ResponseEntity<List<UserDto>> getAllUsers() {
		   this.user_service.getAllUsers();
		   return ResponseEntity.ok(this.user_service.getAllUsers());
	   	  
	   }
	   // GET single user
	   @GetMapping("/{userId}")
	   public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
		   
		  // UserDto userById = this.user_service.getUserById(userId);
	   	  // return new ResponseEntity<UserDto>(userById,HttpStatus.OK);
		   
		   
		   return ResponseEntity.ok(this.user_service.getUserById(userId)); // alternate
	   }
	   
}
