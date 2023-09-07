package com.webapp.eshoponline.service;

import java.util.List;

import com.webapp.eshoponline.model.Cart;

public interface ICartService {
	
	void addCart(Cart cart);
	List<Cart> getAll();
	
	void deleteItemFromCart(Long id);
	
	void deleteCart();

}
