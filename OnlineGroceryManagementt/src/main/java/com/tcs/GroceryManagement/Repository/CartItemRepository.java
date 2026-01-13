package com.tcs.GroceryManagement.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.GroceryManagement.Entity.CartItem;
import com.tcs.GroceryManagement.Entity.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
	
	Optional<CartItem> findByProduct(Product product);
}
