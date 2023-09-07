package com.webapp.eshoponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.eshoponline.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	
	Category findById(long id);
	Category findByName(String name);

}
