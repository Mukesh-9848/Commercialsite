package com.webapp.eshoponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.eshoponline.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	

}
