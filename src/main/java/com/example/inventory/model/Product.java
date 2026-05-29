package com.example.inventory.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String brand;
    private String variant;
    private int quantity;
    private int threshold;
    private double price;

    private LocalDateTime createdat;

    public Product(){}

    public int getId(){ return id; }

    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public String getBrand(){ return brand; }
    public void setBrand(String brand){ this.brand = brand; }

    public String getVariant(){ return variant; }
    public void setVariant(String variant){ this.variant = variant; }

    public int getQuantity(){ return quantity; }
    public void setQuantity(int quantity){ this.quantity = quantity; }

    public int getThreshold(){ return threshold; }
    public void setThreshold(int threshold){ this.threshold = threshold; }

    public double getPrice(){ return price; }
    public void setPrice(double price){ this.price = price; }

	public LocalDateTime getCreatedat() {
		return createdat;
	}

	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}
}