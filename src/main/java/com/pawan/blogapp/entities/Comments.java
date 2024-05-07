package com.pawan.blogapp.entities;



import jakarta.persistence.Column;


import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name= "Comments_Details")
public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, length=200)
	private String content;
	
	@ManyToOne
	private Posts post;
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="user_Id") private User user;
	 */
}
