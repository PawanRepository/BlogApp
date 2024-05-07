package com.pawan.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.blogapp.entities.Category;
import com.pawan.blogapp.exceptions.ResourceNotFoundException;
import com.pawan.blogapp.payloads.CategoryDto;
import com.pawan.blogapp.repositories.Category_Repo;
import com.pawan.blogapp.service.Category_service;

@Service
public class CategoryServiceImpl implements Category_service{

	@Autowired
	private Category_Repo category_Repo;
	
	@Autowired
	private ModelMapper modelmapper;
		
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto){
		 Category category = this.dtoToCategory(categoryDto);
		Category savedCategory = this.category_Repo.save(category);
		return this.CategorytoDto(savedCategory);
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category category = this.category_Repo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException ("category" , "id" , categoryId));
		
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDesc(categoryDto.getCategoryDesc());
		
		Category updatedCateory = this.category_Repo.save(category);
		CategoryDto categorytoDto = this.CategorytoDto(updatedCateory);
		return categorytoDto;
		
		
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.category_Repo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category" , "id" , categoryId));
		this.category_Repo.delete(category);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category category = this.category_Repo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category" , "id" , categoryId));
		return this.CategorytoDto(category);
		
		
	}

	@Override
	public List<CategoryDto> getCategories() {
		
		List<Category> categories = this.category_Repo.findAll();
		List<CategoryDto> categoryDto =categories.stream().map(category->this.CategorytoDto(category)).collect(Collectors.toList());
		
		return categoryDto;
		
	}
	
	public Category dtoToCategory(CategoryDto categorydto) {
		Category category = modelmapper.map(categorydto, Category.class);
		return category;
	}
	
	public CategoryDto CategorytoDto(Category category) {
		CategoryDto categorydto = modelmapper.map(category, CategoryDto.class);
		return categorydto;
	}


}
