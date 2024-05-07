package com.pawan.blogapp.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CommentDto {
	
	private int id;
	
	private String content;
	
	private PostsDto postDto;
	
//	private UserDto userDto;
	
}
