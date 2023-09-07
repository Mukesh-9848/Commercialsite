package com.webapp.eshoponline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.eshoponline.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByCategory(Long id);
	Product findByName(String Name);
	

}
