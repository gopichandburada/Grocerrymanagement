package com.tcs.GroceryManagement.Service;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.GroceryManagement.Entity.Cart;
import com.tcs.GroceryManagement.Entity.CartItem;
import com.tcs.GroceryManagement.Entity.Customer;
import com.tcs.GroceryManagement.Entity.Product;
import com.tcs.GroceryManagement.Repository.CartItemRepository;
import com.tcs.GroceryManagement.Repository.CartRepository;
import com.tcs.GroceryManagement.Repository.CustomerRepository;
import com.tcs.GroceryManagement.Repository.ProductRepository;

@Service
public class CartService {

	@Autowired
	public CartRepository cartrepo;
	
	@Autowired
	public CustomerRepository custrepo;
	
	@Autowired
	public CartItemRepository cartitemrepo;
	@Autowired
	public ProductRepository prodrepo;
	
	// Method: Add a product to the customer's active cart
	// Params: customerid (int), productid (int), itemquantity (int)
	// Returns: Cart object after adding the product
	public Cart AddToCart(int customerid, int productid, int itemquantity) {
		Optional<Customer> c = custrepo.findById(customerid);
		Optional<Product> prod = prodrepo.findById(productid);
		if(c.isEmpty() || prod.isEmpty()) {
			return null;
		}
		Customer customer = c.get();
		Product p = prod.get();
		
		Cart cart = customer.getCarts().stream().filter(i->
				i.getFlag()!=1).findFirst().orElse(null);
		
		if(cart == null) {
			cart = new Cart();
			cart.setCustomer(customer);
			cart.setItems(new ArrayList<CartItem>());
			cartrepo.save(cart);
		}
		
		CartItem existingItem = cart.getItems().stream().filter(
				i -> i.getProduct().getProductid() == productid
				).findFirst().orElse(null);
		
		if(existingItem!=null) {
			existingItem.setItemquantity(existingItem.getItemquantity() + itemquantity);
			p.setQuantity(p.getQuantity()-1);
			prodrepo.save(p);
			cartitemrepo.save(existingItem);
		}
		else {
			CartItem newItem = new CartItem();
			newItem.setProduct(p);
			newItem.setItemquantity(itemquantity);
			newItem.setCart(cart);
			p.setQuantity(p.getQuantity()-1);
			prodrepo.save(p);
			cartitemrepo.save(newItem);
			cart.getItems().add(newItem);
		}
		customer.getCarts().add(cart);
		cartrepo.save(cart);
		custrepo.save(customer);
		return cart;
	}
	
	// Method: Increment product quantity in customer's cart
	// Params: productid (int), customerid (int)
	// Returns: Updated Cart object
	public Cart IncrementItem(int productid, int customerid) {
		Optional<Customer> opcust = custrepo.findById(customerid);
		if(opcust == null) {
			return null;
		}
		Customer customer = opcust.get();
		Optional<Product> prod = prodrepo.findById(productid);

		Cart cart = customer.getCarts().stream().filter(i->
		i.getFlag()!=1).findFirst().orElse(null);
		Product product = prod.get();
		
		CartItem item = new CartItem();
		if(product.getQuantity()<=0){
			return null;
		}
		for(CartItem cartitem : cart.getItems()) {
			if(cartitem.getProduct() == product) {
				cartitem.setItemquantity(cartitem.getItemquantity()+1);
				product.setQuantity(product.getQuantity()-1);
				item = cartitem;
				break;
			}
		}
		cartitemrepo.save(item);
		prodrepo.save(product);
		return cartrepo.save(cart);	
	}
	
	// Method: Decrement product quantity in customer's cart
	// Params: productid (int), customerid (int)
	// Returns: Updated Cart object OR null if not found
	public Cart DecrementItem(int productid, int customerid) {
		Optional<Customer> opcust = custrepo.findById(customerid);
		if(opcust == null) {
			return null;
		}
		Customer customer = opcust.get();
		Optional<Product> prod = prodrepo.findById(productid);
		Product product = prod.get();
		
		Cart cart = customer.getCarts().stream().filter(i->
		i.getFlag()!=1).findFirst().orElse(null);
		if(cart == null) {
			return null;
		}
		CartItem item = new CartItem();
		for(CartItem cartitem : cart.getItems()) {
			if(cartitem.getProduct().getProductid() == product.getProductid()) {
				if(cartitem.getItemquantity()<=1) {
					cart.getItems().remove(cartitem);
					product.setQuantity(product.getQuantity()+1);
					prodrepo.save(product);
					cartitemrepo.delete(cartitem);
				}
				else {
					cartitem.setItemquantity(cartitem.getItemquantity()-1);
					product.setQuantity(product.getQuantity()+1);
					item = cartitem;
					prodrepo.save(product);
					cartitemrepo.save(item);
					break;
				}
				
			}
		}
		return cartrepo.save(cart);
		
	}
	
	// Method: View complete cart history for a customer
	// Param: customerid (int)
	// Returns: Customer object with cart history
	public Customer ViewCartHistory(int customerid){
		Optional<Customer> opcust = custrepo.findById(customerid);
		if(opcust == null) {
			return null;
		}
		Customer customer = opcust.get();
		
		return customer;
	}
}