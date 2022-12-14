package com.example.commonbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@DiscriminatorValue("0")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProductID", nullable = false)
	private int productID;
	@Column(name = "ProductName")
	private String productName;
	@Column(name = "CategoryID")
	private int categoryID;
	@Column(name = "UnitPrice", nullable = false)
	private Double unitPrice;
	@Column(name = "QuantityPerUnit")
	private String quantityPerUnit;
	@Column(name = "UnitsInStock")
	private Integer unitsInStock;
}
