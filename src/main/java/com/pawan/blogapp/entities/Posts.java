package com.pawan.blogapp.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "posts")
@NoArgsConstructor
@Getter
@Setter
public class Posts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name = "Post_title", nullable = false, length = 100)
	private String title;
	
	private String postImage;
	
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "category_Id")
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="post", cascade = CascadeType.ALL)
	private List<Comments> comments= new ArrayList<>();
}
