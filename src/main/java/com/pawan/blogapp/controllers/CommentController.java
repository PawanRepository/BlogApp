package com.pawan.blogapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.blogapp.payloads.CommentDto;

import com.pawan.blogapp.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/comment/")
	public ResponseEntity<CommentDto> doComment(
					@RequestBody CommentDto commentDto,
				//	@PathVariable Integer userId,
					@PathVariable Integer postId){
		
		CommentDto doComment = this.commentService.doComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto> (doComment,HttpStatus.CREATED);
	}
	
}
