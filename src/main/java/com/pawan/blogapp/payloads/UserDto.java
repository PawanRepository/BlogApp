package com.pawan.blogapp.payloads;


import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {
	
	private int id;
	
	
	@NotEmpty
	@Size(min=5,max=10,message = "username must not be less than 5 chars and maximum of 10 chars")
	private String name;
	
	@Email
	private String email;
	
	private String password;	
	
	private String about;
	
	
	
}
