package com.pawan.blogapp.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class PostsDto {
	
	private Integer postId;
	
	private String title;
	
	private String postImage;
	
	private Date date;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private List<CommentDto> comment = new ArrayList<>() ;

}
