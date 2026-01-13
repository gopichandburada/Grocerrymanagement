package com.tcs.GroceryManagement.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cartid;

    /**
     * Cart status:
     * 0 = Active (in progress)
     * 1 = Purchased/Completed
     */
    int flag = 0;

    @ManyToOne
    @JoinColumn(name = "customerid")
    @JsonBackReference
    Customer customer;
//    @JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    List<CartItem> items;

    public Cart() {}

    public Cart(Customer customer, List<CartItem> items) {
        this.customer = customer;
        this.items = items;
    }

    // ---------------- Getters & Setters ----------------
    public int getCartid() {
        return cartid;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
