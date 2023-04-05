package com.examportal.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.dao.CategoryRepository;
import com.examportal.model.Category;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {

		return this.categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {

		return this.categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategory() {

		return this.categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(Long cId) {

		return this.categoryRepository.findById(cId).get();
	}

	@Override
	public void deleteCategory(Long cId) {
		Category category = new Category();
		category.setcId(cId);
		this.categoryRepository.delete(category);
	}

}
