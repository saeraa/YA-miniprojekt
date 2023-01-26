package com.example.commonbackend.model;

import jakarta.persistence.*;

@MappedSuperclass
public class SuperProduct {

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

}
