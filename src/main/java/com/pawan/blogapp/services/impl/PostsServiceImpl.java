package com.pawan.blogapp.services.impl;



import java.util.Date;



import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.pawan.blogapp.entities.Category;
import com.pawan.blogapp.entities.Posts;
import com.pawan.blogapp.entities.User;
import com.pawan.blogapp.exceptions.ResourceNotFoundException;
import com.pawan.blogapp.payloads.PostResponse;
import com.pawan.blogapp.payloads.PostsDto;
import com.pawan.blogapp.repositories.Category_Repo;
import com.pawan.blogapp.repositories.Post_Repo;
import com.pawan.blogapp.repositories.User_Repo;
import com.pawan.blogapp.service.Posts_service;


@Service
public class PostsServiceImpl implements Posts_service{
	
	@Autowired
	private Post_Repo postrepo;
	
	@Autowired
	private User_Repo user_repo;
	
	@Autowired
	private Category_Repo category_Repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
		
	@Override
	public PostsDto createPost(PostsDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.user_repo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
		Category category = this.category_Repo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "id", categoryId));
		
		Posts posts = this.modelMapper.map(postDto, Posts.class);
		posts.setPostImage("default.png");
		posts.setDate(new Date());
		posts.setCategory(category);
		posts.setUser(user);
		
		Posts saveposts = this.postrepo.save(posts);
		PostsDto postsDto = this.modelMapper.map(saveposts, PostsDto.class);
		return postsDto;
	}

	@Override
	public PostsDto updatePost(PostsDto postsDto, Integer postsId) {
		Posts posts = this.postrepo.findById(postsId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postsId));
		
		posts.setTitle(postsDto.getTitle());
		posts.setPostImage(posts.getPostImage());
		
		Posts save = this.postrepo.save(posts);
		return this.modelMapper.map(save, PostsDto.class);
	}

	@Override
	public void delete(Integer postsId) {
		Posts posts = this.postrepo.findById(postsId).orElseThrow(()-> new ResourceNotFoundException("Integer", "postId", postsId));
		this.modelMapper.map(posts, PostsDto.class);
		
	}

	@Override
	public PostsDto getPostsById(Integer postsId) {
		Posts posts = this.postrepo.findById(postsId).orElseThrow(()->new ResourceNotFoundException("Integer", "postId", postsId));
		return this.modelMapper.map(posts,PostsDto.class);
		
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortByDir) {
		
		org.springframework.data.domain.Sort sort = null;
		
		sort=(sortByDir.equalsIgnoreCase("asc"))?org.springframework.data.domain.Sort.by(sortBy).ascending():org.springframework.data.domain.Sort.by(sortBy).descending();
					
		Pageable page = PageRequest.of(pageNumber, pageSize, sort );
		Page<Posts> pages = this.postrepo.findAll(page);
		List<Posts> posts = pages.getContent();
		List<PostsDto> postDto = posts.stream().map((post)-> this.modelMapper.map(post,PostsDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDto);
		postResponse.setPageNumber(page.getPageNumber());
		postResponse.setPageSize(page.getPageSize());
		postResponse.setTotalElements(pages.getTotalElements());
		postResponse.setTotalPages(pages.getTotalPages()); 
		postResponse.setLastPage(pages.isLast());
		
		return postResponse;
	}

	@Override
	public PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {  
		
		Category category = this.category_Repo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "id",categoryId));
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Posts> pages = this.postrepo.findByCategory(category, pageable);
		List<Posts> posts = pages.getContent();
		List<PostsDto> postsDto = posts.stream().map((post)->this.modelMapper.map(post,PostsDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postsDto);
		postResponse.setPageNumber(pages.getNumber());
		postResponse.setPageSize(pages.getSize());
		postResponse.setTotalElements(pages.getTotalElements());
		postResponse.setTotalPages(pages.getTotalPages()); 
		postResponse.setLastPage(pages.isLast());
		
		return postResponse;
		
	}

	
	  @Override 
	  public PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize ) {
		  User user =this.user_repo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
		  
		  	Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Posts> pages = this.postrepo.findByUser(user, pageable);
			List<Posts> posts = pages.getContent();
			List<PostsDto> postsDto = posts.stream().map((post)->this.modelMapper.map(post,PostsDto.class)).collect(Collectors.toList());
			 
			PostResponse postResponse = new PostResponse();
			
			postResponse.setContent(postsDto);
			postResponse.setPageNumber(pages.getNumber());
			postResponse.setPageSize(pages.getSize());
			postResponse.setTotalElements(pages.getTotalElements());
			postResponse.setTotalPages(pages.getTotalPages()); 
			postResponse.setLastPage(pages.isLast());
			
			return postResponse;
			
	  }

	@Override
	public List<PostsDto> searchPostsByTitle(String keyword) {
		List<Posts> posts = this.postrepo.findByTitleContaining(keyword);
		List<PostsDto> postsDto = posts.stream().map((post)->this.modelMapper.map(post, PostsDto.class)).collect(Collectors.toList());
		return postsDto;
	}

	
}
