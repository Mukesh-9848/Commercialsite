package com.webapp.eshoponline.service;


import java.util.List;

import com.webapp.eshoponline.model.Product;

public interface IProductService {

	void addProduct(Product product);
    List<Product> getAllProducts();
    List<Product> getProductByCategory(Long id);
    Product getByName(String name);
    void deleteById(Long id);
	
}
