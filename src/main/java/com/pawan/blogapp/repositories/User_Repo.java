package com.pawan.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pawan.blogapp.entities.User;

public interface User_Repo extends JpaRepository<User, Integer>{
	
	

}
