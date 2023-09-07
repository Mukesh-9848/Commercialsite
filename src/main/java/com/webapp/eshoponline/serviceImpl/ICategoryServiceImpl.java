package com.webapp.eshoponline.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.eshoponline.model.Category;
import com.webapp.eshoponline.repository.CategoryRepository;
import com.webapp.eshoponline.service.ICategoryService;

@Service
public class ICategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public void addCategory(Category category) {
		categoryRepo.save(category);
	}

	

	@Override
	public Category getCategoryById(Long categoryId) {
		return categoryRepo.getById(categoryId);
	}

	@Override
	public Category getCategoryByName(String name) {
		return categoryRepo.findByName(name);
	}



	@Override
	public List<Category> getAll() {
		return categoryRepo.findAll();
	}

}
