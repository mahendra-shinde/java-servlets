package com.mahendra.entities;

public class Product {
	
	private int productId;
	private String name;
	private double rate;
	private int quantity;
	public Product(int productId, String name, double rate, int quantity) {
		super();
		this.productId = productId;
		this.name = name;
		this.rate = rate;
		this.quantity = quantity;
	}
	public Product() {
		super();
	}
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
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
