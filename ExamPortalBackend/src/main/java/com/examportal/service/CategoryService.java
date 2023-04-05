package com.examportal.service;

import java.util.List;

import com.examportal.model.Category;

public interface CategoryService {

	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public List<Category> getAllCategory();
	
	public Category getCategoryById(Long cId);
	
	public void deleteCategory(Long cId);
}
