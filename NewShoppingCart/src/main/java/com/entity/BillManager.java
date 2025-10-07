package com.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class BillManager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long billno;

	private String username;

	private double total;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt = new Date();

	@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
	private List<ItemTransaction> items;

	// Getters and setters
	public Long getBillno() {
		return billno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<ItemTransaction> getItems() {
		return items;
	}

	public void setItems(List<ItemTransaction> items) {
		this.items = items;
	}
}
