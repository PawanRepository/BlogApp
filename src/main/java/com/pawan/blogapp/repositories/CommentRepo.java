package com.pawan.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawan.blogapp.entities.Comments;

public interface CommentRepo extends JpaRepository<Comments, Integer>{

}
