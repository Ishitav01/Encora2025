package com.entity;

import jakarta.persistence.*;

@Entity
public class ItemTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long productId;
	private String productName;
	private String shop;
	private int quantity;
	private double price;

	@ManyToOne
	@JoinColumn(name = "billno")
	private BillManager bill;

	// Getters and setters
	public Long getId() {
		return id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public BillManager getBill() {
		return bill;
	}

	public void setBill(BillManager bill) {
		this.bill = bill;
	}
}
