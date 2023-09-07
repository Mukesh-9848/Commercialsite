package com.webapp.eshoponline.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.eshoponline.model.Cart;
import com.webapp.eshoponline.repository.CartRepository;
import com.webapp.eshoponline.service.ICartService;

@Service
public class ICartServiceImpl implements ICartService{
	
	@Autowired
	private CartRepository cartRepo;

	@Override
	public void addCart(Cart cart) {
		

		
		cartRepo.save(cart);
	}

	@Override
	public List<Cart> getAll() {
		// TODO Auto-generated method stub
		return cartRepo.findAll();
	}

	@Override
	public void deleteItemFromCart(Long id) {
         cartRepo.deleteById(id);		
	}

	@Override
	public void deleteCart() {
        cartRepo.deleteAll();		
	}


}
