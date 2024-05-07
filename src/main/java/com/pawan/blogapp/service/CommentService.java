package com.pawan.blogapp.service;




import com.pawan.blogapp.payloads.CommentDto;



public interface CommentService {
	
	CommentDto doComment(CommentDto commentDto, Integer postId);
	void deleteComment(Integer commentId);
}
