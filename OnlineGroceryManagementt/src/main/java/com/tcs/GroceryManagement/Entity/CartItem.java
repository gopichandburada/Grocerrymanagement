package com.tcs.GroceryManagement.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int itemid;

    int itemquantity;

    /**
     * Many CartItems can reference one Product
     */
    @ManyToOne
    @JoinColumn(name = "productid")
    Product product;

    /**
     * Many CartItems belong to one Cart
     */
    @ManyToOne
    @JoinColumn(name = "cartid")
    @JsonBackReference
    Cart cart;

    public CartItem() {}

    public CartItem(Product product, Cart cart, int itemquantity) {
        this.product = product;
        this.cart = cart;
        this.itemquantity = itemquantity;
    }

    // ---------------- Getters & Setters ----------------
    public int getItemid() {
        return itemid;
    }

    public int getItemquantity() {
        return itemquantity;
    }

    public void setItemquantity(int itemquantity) {
        this.itemquantity = itemquantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
