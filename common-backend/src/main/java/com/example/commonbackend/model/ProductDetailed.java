package com.example.commonbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class ProductDetailed extends SuperProduct {

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
	@Column(name = "Discontinued")
	private Boolean discontinued;
	@Column(name = "ReorderLevel")
	private Integer reorderLevel;
	@Column(name = "SupplierID")
	private int supplierID;
	@Column(name = "UnitsOnOrder")
	private Integer unitsOnOrder;
}
