package com.webapp.eshoponline.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.eshoponline.model.Product;
import com.webapp.eshoponline.repository.ProductRepository;
import com.webapp.eshoponline.service.IProductService;

@Service
public class IProductServiceImpl implements IProductService{
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	public void addProduct(Product product) {
       productRepo.save(product);
		
	}

	@Override
	public List<Product> getAllProducts() {
		
		return productRepo.findAll();
	}

	@Override
	public List<Product> getProductByCategory(Long id) {
		// TODO Auto-generated method stub
		return productRepo.findByCategory(id);
	}

	@Override
	public Product getByName(String name) {
		return productRepo.findByName(name);
	}

	@Override
	public void deleteById(Long id) {
		productRepo.deleteById(id);
		
	}



}
