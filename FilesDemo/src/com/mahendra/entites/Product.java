package com.mahendra.entites;

import java.io.Serializable;

public class Product implements Serializable {
	private int productId;
	private String name;
	private int quantity;
	private double rate;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int productId, String name, int quantity, double rate) {
		super();
		this.productId = productId;
		this.name = name;
		this.quantity = quantity;
		this.rate = rate;
	}
	
	
}
