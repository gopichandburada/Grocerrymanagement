package com.tcs.GroceryManagement.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.tcs.GroceryManagement.Entity.Customer;
import com.tcs.GroceryManagement.FileLog.FileLog;
import com.tcs.GroceryManagement.Service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
	public CustomerService custservice;
	
	// Method: Fetch and return list of all customers
	// Returns: List<Customer>
	@GetMapping("/ViewAllCustomers")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Customer> ViewAllCustomers(){
		FileLog.log("Customer List Loaded");
		return custservice.ViewAllCustomers();
	}
	
	// Method: Add new customer to database
	// Param: Customer object from request body
	// Returns: ResponseEntity with saved customer OR error message
	@PostMapping("/AddCustomer")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> AddCustomer(@RequestBody Customer customer) {
		FileLog.log("Customer Added");
	    try {
	        Customer savedCustomer = custservice.AddCustomer(customer);
	        return ResponseEntity.ok(savedCustomer);
	    } catch (Exception e) { 
	        if (e.getMessage().equals("Email already exists")) {
	            return ResponseEntity
	                    .status(HttpStatus.CONFLICT) 
	                    .body(e.getMessage());
	        }
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Something went wrong: " + e.getMessage());
	    }
	}

	// Method: Login customer with email/password
	// Param: Customer object with login details
	// Returns: int (status code from service layer)
	@PostMapping("/LoginCustomer")
	@CrossOrigin(origins = "http://localhost:4200")
	public int LoggingCustomer(@RequestBody Customer customer) {
		FileLog.log("Customer Logging");
		return custservice.LoggingCustomer(customer);
	}
	
	// Method: Get cart of a specific customer
	// Param: customerid (int)
	// Returns: Customer object with cart details
	@GetMapping("/ViewCart")
	@CrossOrigin(origins = "http://localhost:4200")
	public Customer GetCustomerCart(@RequestParam int customerid) {
		FileLog.log("Customer Cart is Loaded");
		return custservice.GetCustomerCart(customerid);
	}
	

	// Method: Process payment for a customer
	// Param: customerid (int)
	// Returns: Customer object with updated payment status
	@PostMapping("/Payment")
	@CrossOrigin(origins = "http://localhost:4200")
	public Customer Payment(@RequestParam int customerid) {
		FileLog.log("Customer Payment Done");
		return custservice.Payment(customerid);
	}
	
	
	// Method: Fetch profile details of a specific customer
	// Param: customerid (int)
	// Returns: Customer object (profile details)
	@GetMapping("/YourProfile")
	@CrossOrigin(origins = "http://localhost:4200")
	public Customer YourProfile(@RequestParam int customerid) {
		FileLog.log("Customer Profile Called");
		return custservice.YourProfile(customerid);
	}
	
	// Method: Update profile of a specific customer
	// Params: customerid (int), updated Customer object
	// Returns: ResponseEntity with updated Customer OR error
	@PutMapping("/Updateprofile")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> updateProfile(@RequestParam int customerid, @RequestBody Customer customer) {
        try {
        	FileLog.log("Customer Profile Updated");
            Customer updated = custservice.UpdateProfile(customerid, customer);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
        	FileLog.log("Customer Profile Update Failed");
            if (e.getMessage().equals("Email already exists")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

	// Method: Reset password for customer
	// Params: pass (String), cpass (String), loginemail (String)
	// Returns: int (status code from service layer)
	@PostMapping("/ResetPassword")
	@CrossOrigin(origins = "http://localhost:4200")
	public int ResetPassword(@RequestParam String pass, @RequestParam String cpass,@RequestParam String loginemail) {
		FileLog.log("Customer Reset Password Called");
		return custservice.ResetPassword(pass, cpass, loginemail);
	}
}
