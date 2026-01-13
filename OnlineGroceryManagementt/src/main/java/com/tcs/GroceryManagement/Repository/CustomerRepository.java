package com.tcs.GroceryManagement.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.GroceryManagement.Entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Optional<Customer> findByEmail(String email); 
	//List<Customer> findByActive(String status);
}