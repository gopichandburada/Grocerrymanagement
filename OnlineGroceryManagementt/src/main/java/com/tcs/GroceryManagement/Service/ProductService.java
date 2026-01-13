package com.tcs.GroceryManagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.GroceryManagement.Entity.Product;
import com.tcs.GroceryManagement.Repository.ProductRepository;
import java.util.*;
@Service
public class ProductService {
	@Autowired
	public ProductRepository productrepo;
	
	// Method: Get all products
	// Returns: List<Product>
	public List<Product> ViewAllProducts(){
		return productrepo.findAll();
	}
	
	// Method: Add a new product OR update quantity if already exists
	// Param: Product object
	// Returns: Saved/Updated Product
	public Product AddProduct(Product product) {
		Product prod = productrepo.findByProductname(product.getProductname()).orElse(null);
		if(prod!=null) {
			prod.setQuantity(prod.getQuantity() + product.getQuantity());
			return productrepo.save(prod);
		}
		return productrepo.save(product);
	}
	
	// Method: View a specific product by ID
	// Param: id (int)
	// Returns: Product OR null if not found
	public Product ViewSpecificProduct(int id) {
		return productrepo.findById(id).orElse(null);
	}
	
	// Method: Update product details
	// Params: productid (int), Product object with new data
	// Returns: Updated Product
	public Product UpdateProduct(int productid, Product product) {
		System.out.println("GOING IN");
		Product oldProduct  = productrepo.findById(productid).orElse(null);
		oldProduct.setProductname(product.getProductname());
		oldProduct.setCategory(product.getCategory());
		oldProduct.setPrice(product.getPrice());
		oldProduct.setQuantity( product.getQuantity());
		System.out.println(product.getCategory());
		return productrepo.save(oldProduct);
	}
	
	// Method: Delete product by setting delprod=1 and quantity=0
	// Param: productid (int)
	// Returns: void
	public void DeleteProduct(int productid){
		Product prod = productrepo.findById(productid).orElse(null);
		prod.setDelprod(1);
		prod.setQuantity(0);
		productrepo.save(prod);
	}
	
	// Method: Get distinct product categories
	// Returns: List<String> categories
	public List<String> getCategories(){
		return productrepo.findDistinctCategories();
	}
	
	// Method: Search products by category and keyword
	// Params: category (String), keyword (String)
	// Returns: List<Product>
	public List<Product> searchProductsByCategory(String category, String keyword){
		return productrepo.findByCategoryIgnoreCaseAndProductnameContainingIgnoreCase(category, keyword);
	}
	
	// Method: Get all products by category
	// Param: category (String)
	// Returns: List<Product>
	public List<Product> getProductByCategory(String category){
		return productrepo.findByCategoryIgnoreCase(category);
	}
	
	// Method: Search products by name
	// Param: keyword (String)
	// Returns: List<Product>
	public List<Product> searchProductsByName(String keyword){
		return productrepo.findByProductnameIgnoreCase(keyword);
	}
	
	// Method: Activate Product
	// Param: productid (int)
	// Returns: int
	public int ActivateProduct(int productid) {
		Product prod = productrepo.findById(productid).orElse(null);
		prod.setDelprod(0);
		productrepo.save(prod);
		return 1;
	}
}