package com.tcs.GroceryManagement.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.GroceryManagement.Entity.Cart;
import com.tcs.GroceryManagement.Entity.Customer;
import com.tcs.GroceryManagement.FileLog.FileLog;
import com.tcs.GroceryManagement.Service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
	@Autowired
	public CartService cartservice;
	
	// Method: Add product to cart
	// Params: customerid (int), productid (int), itemquantity (int)
	// Returns: Cart object (updated)
	@PostMapping("/AddItems")
	@CrossOrigin(origins = "http://localhost:4200")
	public Cart AddToCart(@RequestParam int customerid, @RequestParam int productid, @RequestParam int itemquantity) {
		FileLog.log("added to cart");
		return cartservice.AddToCart(customerid, productid,itemquantity);
	}
	
	// Method: Increment quantity of a product in cart
	// Params: productid (int), customerid (int)
	// Returns: Cart object (updated)
	@PostMapping("/IncrementItem")
	@CrossOrigin(origins = "http://localhost:4200")
	public Cart IncrementItem(@RequestParam int productid, @RequestParam int customerid) {
		FileLog.log("product quantity increased");
		return cartservice.IncrementItem(productid, customerid);
	}
	
	// Method: Decrement quantity of a product in cart
	// Params: productid (int), customerid (int)
	// Returns: Cart object (updated)
	@PutMapping("/DecrementItem")
	@CrossOrigin(origins = "http://localhost:4200")
	public Cart DecrementItem(@RequestParam int productid, @RequestParam int customerid) {
		FileLog.log("product quantity decreased");
		return cartservice.DecrementItem(productid, customerid);
	}
	
	// Method: View full cart history of a customer
	// Param: customerid (int)
	// Returns: Customer object with cart history
	@GetMapping("/ViewHistory")
	@CrossOrigin(origins = "http://localhost:4200")
	public Customer ViewCartHistory(@RequestParam int customerid){
		FileLog.log("View Cart History Called");
		return cartservice.ViewCartHistory(customerid);
	}
	
}
