package com.pawan.blogapp.service;

import java.util.List;



import com.pawan.blogapp.payloads.CategoryDto;


public interface Category_service {
	
	 CategoryDto createCategory(CategoryDto categoryDto);
	
	 CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	 void deleteCategory(Integer categoryId);
	
	List<CategoryDto> getCategories();

	CategoryDto getCategory(Integer categoryId);
	
	
	

}
