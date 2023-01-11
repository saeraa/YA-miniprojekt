package com.example.commonbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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
