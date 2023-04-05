package com.examportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
