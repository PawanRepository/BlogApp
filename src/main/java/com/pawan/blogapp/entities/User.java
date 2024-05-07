package com.pawan.blogapp.entities;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity  
@Table(name="users")     // by default table is created with class name, if we want to change then use @Table
@NoArgsConstructor 
@Getter
@Setter
public class User {

	@Id  // 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "user_name" , nullable = false , length = 100)
	private String name;
	
	@Column(nullable = false , length = 100)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	private String about; 
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Posts> posts = new ArrayList<Posts>();
	/*
	 * @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) private
	 * List<Comments> comments = new ArrayList<>();
	 */
}
