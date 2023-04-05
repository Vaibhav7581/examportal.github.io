package com.examportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.model.Category;
import com.examportal.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// Add category
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		Category addCategory = this.categoryService.addCategory(category);
		return ResponseEntity.ok(addCategory);
	}
	

	// Get category
	@GetMapping("/{cid}")
	public Category getCategoryById(@PathVariable Long cid) {
		return this.categoryService.getCategoryById(cid);
	}

	// get All Category
	@GetMapping("/")
	public List<Category> getAllCategory() {
		return this.categoryService.getAllCategory();
	}

	// update Category

	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		return this.categoryService.updateCategory(category);
	}

	
	// delete Category

	@DeleteMapping("/{cid}")
	public void deleteCategory(Long cid) {
		this.categoryService.deleteCategory(cid);
	}

}
