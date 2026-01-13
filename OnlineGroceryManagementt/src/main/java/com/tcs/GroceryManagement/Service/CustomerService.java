package com.tcs.GroceryManagement.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.tcs.GroceryManagement.exception.EmailAlreadyExistsException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.tcs.GroceryManagement.Entity.Cart;
import com.tcs.GroceryManagement.Entity.Customer;

import com.tcs.GroceryManagement.Repository.CartRepository;
import com.tcs.GroceryManagement.Repository.CustomerRepository;
import java.util.*;


@Service
public class CustomerService {
	@Autowired
	public CustomerRepository custrepo;
	@Autowired
	public CartRepository cartrepo;
	@Autowired
	public BCryptPasswordEncoder passwordencoder;
	
	// Method: Get list of all customers
	// Returns: List<Customer>
	public List<Customer> ViewAllCustomers(){
		List<Customer> customer = new ArrayList<>();
		customer = custrepo.findAll();
		return customer;
	}
	
	// Method: Add new customer after checking if email exists
	// Param: Customer object
	// Returns: Saved Customer OR throws Exception if email exists
	public Customer AddCustomer(Customer customer) {
		Customer existing = custrepo.findByEmail(customer.getEmail()).orElse(null);
        if (existing != null) {
        	throw new EmailAlreadyExistsException("Email already exists");

        }
		customer.setPassword(passwordencoder.encode(customer.getPassword()));
		return custrepo.save(customer);
	}
	
	// Method: Customer/Admin login
	// Param: Customer object with login credentials
	// Returns: int (adminid+100 if admin, customerid if customer, error codes otherwise)
	public int LoggingCustomer(Customer customer) {

	    // admin login (hardcoded)
	    String adminEmail = "admin@example.com";      // your admin email
	    String adminPassword = "Admin@123";           // your admin password

	    if (customer.getEmail().equals(adminEmail) && customer.getPassword().equals(adminPassword)) {
	        return 101; 
	    }

	    // ✅ CUSTOMER LOGIN
	    Customer customerLoged = custrepo.findByEmail(customer.getEmail()).orElse(null);

	    if (customerLoged == null) {
	        return -2; // user not found
	    }

	    if (!passwordencoder.matches(customer.getPassword(), customerLoged.getPassword())) {
	        return -2; // wrong password
	    }

	    // Optional: you may not need to save again here unless updating last login
	    // custrepo.save(customerLoged);

	    return customerLoged.getCustomerid();
	}


	
	// Method: Fetch customer along with cart by ID
	// Param: customerid (int)
	// Returns: Customer object
	public Customer GetCustomerCart(int customerid) {
		Customer c = custrepo.findById(customerid).orElse(null);
		return c;
	}
	
	// Method: Process customer payment (close active cart)
	// Param: customerid (int)
	// Returns: Updated Customer object
	public Customer Payment(int customerid) {
		Optional<Customer> opcust = custrepo.findById(customerid);
		if(opcust.isEmpty()) {
			return null;
		}
		Customer customer = opcust.get();
		
		Cart activeCart = customer.getCarts().stream().filter(i-> i.getFlag()!=1).findFirst().orElse(null);
		
		activeCart.setFlag(1);
		return custrepo.save(customer);
	}
	
	// Method: Get profile details of customer
	// Param: customerid (int)
	// Returns: Customer object
	public Customer YourProfile(int customerid) {
		return custrepo.findById(customerid).orElse(null);
	}
	
	// Method: Update customer profile details
	// Params: customerid (int), new Customer object
	// Returns: Updated Customer OR throws Exception if email duplicate
	public Customer UpdateProfile(int customerid, Customer customer) throws Exception {
        Customer c = custrepo.findById(customerid).orElse(null);
        if (c == null) {
            throw new Exception("Customer not found");
        }
        Optional<Customer> existing = custrepo.findByEmail(customer.getEmail());
        if (existing.isPresent() && existing.get().getCustomerid() != customerid) {
            throw new Exception("Email already exists");
        }
        c.setCustomername(customer.getCustomername());
        c.setEmail(customer.getEmail());
        c.setAddress(customer.getAddress());
        c.setMobile(customer.getMobile());
        return custrepo.save(c);
    }

	// Method: Reset password for customer
	// Params: pass (String), cpass (String), loginemail (String)
	// Returns: int (status code: -2 not found, -1 old password reused, 0 mismatch, 1 success)
	public int ResetPassword(String pass, String cpass, String loginemail) {
	    Customer customer = custrepo.findByEmail(loginemail).orElse(null);

	    if (customer == null) {
	        return -2;
	    }

	    String currentHashedPassword = customer.getPassword();

	    if (passwordencoder.matches(pass, currentHashedPassword) || 
	        passwordencoder.matches(cpass, currentHashedPassword)) {
	        return -1;
	    }

	    if (!cpass.equals(pass)) {
	        return 0; 
	    }

	    String encrypted = passwordencoder.encode(pass);
	    customer.setPassword(encrypted);
	    custrepo.save(customer);
	    return 1;
	}


}