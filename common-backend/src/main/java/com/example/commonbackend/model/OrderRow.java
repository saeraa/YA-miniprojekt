package com.example.commonbackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "order details")
public class OrderRow {
	@Id
	@Column(name="OrderID")
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
