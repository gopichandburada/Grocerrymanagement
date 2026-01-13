package com.tcs.GroceryManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.GroceryManagement.Entity.Cart;
import com.tcs.GroceryManagement.Entity.Customer;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	Optional<Cart> findByCustomer(Customer customer);
}
