package com.example.commonbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name = "CustomerID")
    private String customerId;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "ContactTitle")
    private String contactTitle;
    @Column(name = "Address")
    private String address;

    @Column(name = "City")
    private String city;

}