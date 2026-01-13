package com.tcs.GroceryManagement.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tcs.GroceryManagement.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("SELECT DISTINCT p.category FROM Product p")
	List<String> findDistinctCategories();
	
	List<Product> findByCategoryIgnoreCase(String category);
	
	Optional<Product> findByProductname(String product);
	
	List<Product> findByCategoryIgnoreCaseAndProductnameContainingIgnoreCase(String category, String keyword);
	
	List<Product> findByProductnameIgnoreCase(String keyword);
}