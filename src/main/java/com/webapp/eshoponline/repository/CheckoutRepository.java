package com.webapp.eshoponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.eshoponline.model.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Long>{

}
