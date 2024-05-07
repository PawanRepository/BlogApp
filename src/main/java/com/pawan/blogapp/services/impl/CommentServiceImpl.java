package com.pawan.blogapp.services.impl;



import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.blogapp.entities.Comments;
import com.pawan.blogapp.entities.Posts;

import com.pawan.blogapp.exceptions.ResourceNotFoundException;
import com.pawan.blogapp.payloads.CommentDto;
import com.pawan.blogapp.repositories.CommentRepo;
import com.pawan.blogapp.repositories.Post_Repo;
import com.pawan.blogapp.repositories.User_Repo;
import com.pawan.blogapp.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private Post_Repo postRepo;
	
//	@Autowired
//	private User_Repo userRepo;
//	
	@Autowired
	private CommentRepo commentrepo;
	
	@Autowired 
	private ModelMapper modelmapper;
	
	
	@Override
	public CommentDto doComment(CommentDto commentDto, Integer postId) {
		Posts post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId",postId));
	//	User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId",userId));
		
		Comments comments = this.modelmapper.map(commentDto, Comments.class);
		comments.setPost(post);
	//	comments.setUser(user);
		Comments savedComment = this.commentrepo.save(comments);
		
		return this.modelmapper.map(savedComment, CommentDto.class);
	}
	@Override
	public void deleteComment(Integer commentId) {
			Comments deleteComment = this.commentrepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "commentId",commentId));
			this.commentrepo.delete(deleteComment);
	}
	@Override
	public String msg(String s) {
		s= "ignore this msg";
		return s;
	}


}