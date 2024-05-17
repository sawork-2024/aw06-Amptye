package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String pid;
    private String name;
    private double price;
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private int quantity;
    private boolean stock;

    public Product(){}

    public Product(String pid, String name, double price, String image) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = null;
        this.quantity = 10;
        this.stock = true;
    }

    public Product(String pid, String name, double price, String image, Category category, int quantity, boolean stock) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
        this.quantity = quantity;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return getId() + "\t" + getName() + "\t" + getPrice();
    }

}
