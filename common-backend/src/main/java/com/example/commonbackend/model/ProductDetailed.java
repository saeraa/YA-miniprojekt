package com.example.commonbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("1")
public class ProductDetailed extends Product {
	@Column(name = "Discontinued")
	private Boolean discontinued;
	@Column(name = "ReorderLevel", nullable = false)
	private Integer reorderLevel;
	@Column(name = "SupplierID")
	private int supplierID;
	@Column(name = "UnitsOnOrder")
	private Integer unitsOnOrder;
}
