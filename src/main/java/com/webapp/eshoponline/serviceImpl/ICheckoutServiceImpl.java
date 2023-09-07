package com.webapp.eshoponline.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.eshoponline.model.Checkout;
import com.webapp.eshoponline.repository.CheckoutRepository;
import com.webapp.eshoponline.service.ICheckoutService;

@Service
public class ICheckoutServiceImpl implements ICheckoutService{

	@Autowired
	private CheckoutRepository checkoutRepo;
	
	@Override
	public void saveCheckoutInfo(Checkout checkout) {
        		
		checkoutRepo.save(checkout);
	}

}
