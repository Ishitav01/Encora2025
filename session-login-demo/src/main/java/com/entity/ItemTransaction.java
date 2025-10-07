package com.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "item_transaction")
public class ItemTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int productId; // changed to int
	private Integer qty;
	private Double price;

	@ManyToOne
	@JoinColumn(name = "billno") // foreign key
	private BillManager bill;

	// getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public BillManager getBill() {
		return bill;
	}

	public void setBill(BillManager bill) {
		this.bill = bill;
	}
}
