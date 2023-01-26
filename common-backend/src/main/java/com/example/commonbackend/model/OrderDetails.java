package com.example.commonbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "order details extended")
public class OrderDetails {

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
    @Column(name="ProductName")
    private String productName;
}
