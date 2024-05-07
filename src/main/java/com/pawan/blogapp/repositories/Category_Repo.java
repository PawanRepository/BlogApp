package com.pawan.blogapp.repositories;




import org.springframework.data.jpa.repository.JpaRepository;

import com.pawan.blogapp.entities.Category;


public interface Category_Repo extends JpaRepository<Category, Integer> {
	
}
