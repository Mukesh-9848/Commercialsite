package com.webapp.eshoponline.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_table")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String phone;
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressId")
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "cartId")
	private Cart cart;
	
	
}
