package com.pawan.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.blogapp.payloads.Api_Response;
import com.pawan.blogapp.payloads.CategoryDto;
import com.pawan.blogapp.service.Category_service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/Categories")
public class CategoryController {
	
	@Autowired
	private Category_service category_service;
	
	//POST
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto createCategory = this.category_service.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
		
	}
	
	//GET all categories
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		List<CategoryDto> categories = this.category_service.getCategories();
		return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
	}
	
	// GET single Category
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){

		return ResponseEntity.ok(this.category_service.getCategory(categoryId));
	}
	
	// PUT --- update

	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto catgoryDto,
			@PathVariable("catId") Integer categoryId){
		
		CategoryDto updateCategory = this.category_service.updateCategory(catgoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	// Delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<Api_Response> deleteCategory(@PathVariable Integer catId){
		
		this.category_service.deleteCategory(catId);
		return new ResponseEntity<Api_Response>(new Api_Response("category deleted successfully", true),HttpStatus.OK);
		
	}
	
	
}
