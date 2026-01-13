package com.tcs.GroceryManagement.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int customerid;

    String customername;
    String email;
    String password;
    String address;
    long mobile;

    /**
     * All carts of this customer (both active and completed)
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    List<Cart> carts = new ArrayList<>();
    public Customer() {}

    public Customer(String customername, String email, String password, String address, long mobile,
                    List<Cart> carts) {
        this.customername = customername;
        this.email = email;
        this.password = password;
        this.address = address;
        this.mobile = mobile;
        this.carts = new ArrayList<>();;
    }

    // ---------------- Getters & Setters ----------------
    public int getCustomerid() {
        return customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
   

}
