package com.pawan.blogapp.payloads;

import jakarta.validation.constraints.NotBlank;


import jakarta.validation.constraints.Size;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class CategoryDto {

	
	private int categoryId;
	
	@NotBlank
	@Size(min=2,max=50,message = "title cannot be minimum of 10 chars and maximum of 50 chars")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=10,max=50,message = "title cannot be minimum of 10 chars and maximum of 50 chars")
	private String categoryDesc;
	
	
	
}
