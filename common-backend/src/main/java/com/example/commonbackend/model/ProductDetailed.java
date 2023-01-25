package com.example.commonbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("1")
public class ProductDetailed extends Product {
	@Column(name = "Discontinued")
	private Boolean discontinued;
	@Column(name = "ReorderLevel")
	private Integer reorderLevel;
	@Column(name = "SupplierID")
	private int supplierID;
	@Column(name = "UnitsOnOrder")
	private Integer unitsOnOrder;
}
