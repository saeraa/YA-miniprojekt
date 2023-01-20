package com.example.commonbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderID", nullable = false)
	private int id;

	@Column(name = "CustomerID")
	private String customerID;

//	@Column(name = "EmployeeID")
//	private int employeeID;

//	@Column(name = "Freight")
//	private double freight;

	@Column(name = "OrderDate")
	private Timestamp orderDate;

//	@Column(name = "RequiredDate")
//	private Timestamp requiredDate;

//	@Column(name = "ShipAddress")
//	private String shipAddress;

//	@Column(name = "ShipCity")
//	private String shipCity;

//	@Column(name = "ShipCountry")
//	private String shipCountry;

//	@Column(name = "ShipName")
//	private String shipName;

	@Column(name = "ShippedDate")
	private Timestamp shippedDate;

//	@Column(name = "ShipPostalCode")
//	private String shipPostalCode;

//	@Column(name = "ShipRegion")
//	private String shipRegion;

//	@Column(name = "shipVia", nullable = false)
//	private Integer shipVia;

	public Order (String customerId) {
		this.customerID = customerId;
	}
}