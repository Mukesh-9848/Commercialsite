package com.webapp.eshoponline.service;

import java.util.List;

import com.webapp.eshoponline.model.Category;

public interface ICategoryService {
	
	void addCategory(Category category);
	Category getCategoryByName(String name);
	Category getCategoryById(Long categoryId);
	List<Category> getAll();
	

}
