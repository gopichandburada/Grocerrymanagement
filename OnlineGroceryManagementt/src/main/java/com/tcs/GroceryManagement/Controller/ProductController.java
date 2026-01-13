package com.tcs.GroceryManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.tcs.GroceryManagement.Entity.Product;
import com.tcs.GroceryManagement.FileLog.FileLog;
import com.tcs.GroceryManagement.Service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	
	@Autowired
	public ProductService prodservice;
	
	// Method: Get all products
	// Returns: List<Product>
	@GetMapping("/ViewAllProducts")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Product> viewAllProducts(){
		FileLog.log("Product List is Called");
		return prodservice.ViewAllProducts();
	}
	
	// Method: Add new product
	// Param: Product object from request body
	// Returns: Product (saved object)
	@PostMapping("/AddProduct")
	@CrossOrigin(origins = "http://localhost:4200")
	public Product AddProduct(@RequestBody Product p) {

		FileLog.log("Product Added");
		return prodservice.AddProduct(p);
	}
	

	// Method: Update existing product
	// Params: productid (int), Product object
	// Returns: Updated Product
	@PostMapping("/Updateproduct")
	@CrossOrigin(origins = "http://localhost:4200")
	public Product UpdateProduct( @RequestParam("productid") int productid , @RequestBody Product product) {

		FileLog.log("Product Update is Called");
		return prodservice.UpdateProduct(productid, product);
	}
	
	// Method: View specific product by id
	// Param: productid (int)
	// Returns: Product object
	@GetMapping("/ViewSpecificProduct")
	@CrossOrigin(origins = "http://localhost:4200")
	public Product ViewSpecificProduct(@RequestParam int productid) {

		FileLog.log("Specific Product is Called");
		return prodservice.ViewSpecificProduct(productid);
	}
	
	// Method: Delete product by id
	// Param: productid (int)
	// Returns: void
	@PostMapping("/DeleteProduct")
	@CrossOrigin(origins = "http://localhost:4200")
	public void DeleteProduct(@RequestParam int productid){

		FileLog.log("Delete Product is Called");
		prodservice.DeleteProduct(productid);
		return;
	}
	
	// Method: Get all categories
	// Returns: List<String> (categories)
	@GetMapping("/categories")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<String> getCategories(){

		FileLog.log("Product Category is retrieved");
		return prodservice.getCategories();
	}
	
	// Method: Get products by category
	// Param: category (String)
	// Returns: List<Product>
	@GetMapping("/getCategory")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Product> getByCategory(@RequestParam String category){

		FileLog.log("Product List By Category is Called");
		return prodservice.getProductByCategory(category);
	}
	
	// Method: Search products by category + keyword
	// Params: category (String), keyword (String)
	// Returns: List<Product>
	@GetMapping("/searchByCategory")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Product> searchProductByCategory(@RequestParam String category, @RequestParam String keyword){
		FileLog.log("Search Product List By Category is Called");
		return this.prodservice.searchProductsByCategory(category, keyword);
	}

	// Method: Search products by name
	// Param: keyword (String)
	// Returns: List<Product>
	@GetMapping("/searchByName")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Product> searchProductByName(@RequestParam String keyword){

		FileLog.log("Search by Product name is Called");
		return this.prodservice.searchProductsByName(keyword);
	}
	// Method: Activate Product
	// Param: productid (int)
	// Returns: int
	@PostMapping("/ActivateProduct")
	@CrossOrigin(origins = "http://localhost:4200")
	public int ActivateProduct(@RequestParam int productid) {
		FileLog.log("Activate Product is Called");
		return prodservice.ActivateProduct(productid);
	}

}