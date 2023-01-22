package com.example.commonbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

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
	@Column(name = "UnitPrice")
	private Double unitPrice;
	@Column(name = "QuantityPerUnit")
	private String quantityPerUnit;
	@Column(name = "UnitsInStock")
	private Integer unitsInStock;

	@Column(name = "ReorderLevel")
	private Integer reorderLevel;
}
