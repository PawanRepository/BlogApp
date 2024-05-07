package com.pawan.blogapp.repositories;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pawan.blogapp.entities.Category;
import com.pawan.blogapp.entities.Posts;
import com.pawan.blogapp.entities.User;

public interface Post_Repo extends JpaRepository<Posts, Integer> {
	
	Page<Posts> findByUser(User userId, Pageable pageable);
	Page<Posts> findByCategory(Category category,Pageable pageable );
	
 	List<Posts> findByTitleContaining(String title);
 	
}
