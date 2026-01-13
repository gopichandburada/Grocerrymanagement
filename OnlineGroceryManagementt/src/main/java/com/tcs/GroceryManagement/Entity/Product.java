package com.tcs.GroceryManagement.Entity;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productid;

    String productname;
    String category;
    int quantity;
    int price;
    double rating;
    String url;

    /**
     * Soft delete flag
     * 0 = active, 1 = deleted
     */
    int delprod = 0;

    public Product() {}

    public Product(String productname, String category, int quantity, int price, double rating, String url) {
        this.productname = productname;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.rating = rating;
        this.url = url;
    }

    // ---------------- Getters & Setters ----------------
    public int getProductid() {
        return productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDelprod() {
        return delprod;
    }

    public void setDelprod(int delprod) {
        this.delprod = delprod;
    }
}
