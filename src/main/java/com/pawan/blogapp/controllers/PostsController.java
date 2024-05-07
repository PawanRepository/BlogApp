package com.pawan.blogapp.controllers;

import java.io.IOException;

import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.pawan.blogapp.payloads.Api_Response;
import com.pawan.blogapp.payloads.PostResponse;
import com.pawan.blogapp.payloads.PostsDto;
import com.pawan.blogapp.service.FileUpload_Service;
import com.pawan.blogapp.service.Posts_service;

import jakarta.servlet.http.HttpServletResponse;







@RestController
@RequestMapping("/api/")
public class PostsController { 
	
	@Autowired
	private Posts_service posts_service;
	
	@Autowired
	private FileUpload_Service fileService;
	
	@Value("${project.image}")
	private String path;

		@PostMapping("/user/{userId}/category/{categoryId}/posts")
		public  ResponseEntity<PostsDto> createPosts(@RequestBody PostsDto postsDto, 
									@PathVariable Integer userId,@PathVariable Integer categoryId)
		{
			
			PostsDto createPost = this.posts_service.createPost(postsDto, userId, categoryId);
			return new ResponseEntity<PostsDto>(createPost,HttpStatus.CREATED);
		}
		
		// update 
		@PutMapping("/posts/{postsId}")
		public ResponseEntity<PostsDto> updatePost(@RequestBody PostsDto postsDto, @PathVariable Integer postsId){
			PostsDto updatePost = this.posts_service.updatePost(postsDto, postsId);
			
			return ResponseEntity.ok(updatePost) ;
		}
		// get post by category
		@GetMapping("/category/{categoryId}/posts")
		public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
				@RequestParam (value="pageNumber", defaultValue="0",required = false) Integer pageNumber,
				@RequestParam (value="pageSize", defaultValue="3",required = false) Integer pageSize){
			 	PostResponse postsByCategory = this.posts_service.getPostsByCategory(categoryId, pageNumber, pageSize);
			 return ResponseEntity.ok(postsByCategory);
		}
		
		// get post by user
		
		@GetMapping("/user/{userId}/posts")
		public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
				@RequestParam (value="pageNumber", defaultValue="0",required = false) Integer pageNumber,
				@RequestParam (value="pageSize", defaultValue="3",required = false) Integer pageSize
				){
			 	PostResponse postsByUser = this.posts_service.getPostsByUser(userId, pageNumber, pageSize);
			 return ResponseEntity.ok(postsByUser);
		}
		//delete posts
		
		@DeleteMapping("/{postId}")
		public ResponseEntity<Api_Response> deleteposts(@PathVariable Integer postsId){
			
			this.posts_service.delete(postsId);
			return new ResponseEntity<Api_Response>(new Api_Response("posts deleted successfully",true),HttpStatus.OK);
		}
		
		//get post by ID
		
		@GetMapping("/post/{postId}")
		public ResponseEntity<PostsDto> getPostsById(@PathVariable Integer postId){
			PostsDto postsDto = this.posts_service.getPostsById(postId);
			return new ResponseEntity<PostsDto>(postsDto,HttpStatus.OK);
		}
		
		//get all post
		
		@GetMapping("/posts")
		public ResponseEntity<PostResponse> getAllPosts(
				@RequestParam(value="pageNumber", defaultValue="0",required=false) Integer pageNumber, 
				@RequestParam(value="pageSize", defaultValue="5",required=false) Integer pageSize,
				@RequestParam(value="sortBy", defaultValue="postId", required=false)String sortBy,
				@RequestParam(value="sortByDir", defaultValue="ASC",required=false)String sortByDir)
		{
			
			PostResponse allPosts = this.posts_service.getAllPosts(pageNumber, pageSize,sortBy,sortByDir);
			return new ResponseEntity<PostResponse>(allPosts,HttpStatus.OK);
		
		}
		
		// Search
		@GetMapping("/seach/post/{keywords}")
		public ResponseEntity<List<PostsDto>> searchPostsByTitle(@PathVariable("keywords") String keyowrd){
			
			List<PostsDto> postsDto = this.posts_service.searchPostsByTitle(keyowrd);
			return ResponseEntity.ok(postsDto);
		}
		// method to upload image
		@PostMapping("post/image/upload/{postId}")
		public ResponseEntity<PostsDto> uploadImage(
				@RequestParam ("image") MultipartFile image,
				@PathVariable Integer postId
				) throws IOException{
			
			PostsDto postsDto = this.posts_service.getPostsById(postId);
			String imageName = this.fileService.uploadFile(path, image);	
			postsDto.setPostImage(imageName);
			PostsDto updatedPost = this.posts_service.updatePost(postsDto, postId);
			
			return new ResponseEntity<PostsDto> (updatedPost,HttpStatus.OK);
			
		}
		// method to serve image
		@GetMapping(value = "/post/image/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
		public void getFileDetails(
				@PathVariable String imageName,
				HttpServletResponse response
				)throws IOException{
			
			InputStream fileDetails = this.fileService.getFileDetails(path,imageName);
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(fileDetails, response.getOutputStream());
		}
}
