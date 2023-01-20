package com.example.commonbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "order details")
public class OrderRow {
	@Id
	@JoinColumn(table = "orders", name="OrderID")
	private int orderID;
	@Column(name="ProductID")
	private int productID;
	@Column(name="UnitPrice")
	private double unitPrice;
	@Column(name="Quantity")
	private int quantity;
	@Column(name="Discount")
	private double discount;
	@JoinColumn(table = "products", name="ProductName")
	private String productName;
}
