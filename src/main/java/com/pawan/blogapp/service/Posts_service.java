package com.pawan.blogapp.service;


import java.util.List;

import com.pawan.blogapp.payloads.PostResponse;
import com.pawan.blogapp.payloads.PostsDto;

public interface Posts_service {

	PostsDto createPost(PostsDto postDto, Integer userId, Integer categoryId);
	
	PostsDto updatePost(PostsDto postsDto, Integer postsId);
	
	void delete(Integer postsId);
	
	PostsDto getPostsById(Integer postsId);
	
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortByDir);
	
	PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);
	
	PostResponse getPostsByUser(Integer userId,Integer pageNumber, Integer pageSize);
	
	List<PostsDto> searchPostsByTitle(String keyword);
}
