package com.example.commonbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

	@Column(name = "Freight")
	private double freight;

	@Column(name = "OrderDate")
	private Timestamp orderDate;

	@Column(name = "RequiredDate")
	private Timestamp requiredDate;

	@Column(name = "ShipAddress")
	private String shipAddress;

	@Column(name = "ShipCity")
	private String shipCity;

	@Column(name = "ShipCountry")
	private String shipCountry;

	@Column(name = "ShipName")
	private String shipName;

	@Column(name = "ShippedDate")
	private Timestamp shippedDate;

	@Column(name = "ShipPostalCode")
	private String shipPostalCode;

	@Column(name = "ShipRegion")
	private String shipRegion;

//	@Column(name = "shipVia", nullable = false)
//	private Integer shipVia;

	public Order (String customerId, int productId) {
	}
}
/*
CREATE TABLE `orders` (
  `OrderID` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerID` varchar(5) DEFAULT NULL,
  `EmployeeID` int(11) DEFAULT NULL,
  `OrderDate` datetime DEFAULT NULL,
  `RequiredDate` datetime DEFAULT NULL,
  `ShippedDate` datetime DEFAULT NULL,
  `ShipVia` int(11) DEFAULT NULL,
  `Freight` decimal(10,4) DEFAULT '0.0000',
  `ShipName` varchar(40) DEFAULT NULL,
  `ShipAddress` varchar(60) DEFAULT NULL,
  `ShipCity` varchar(15) DEFAULT NULL,
  `ShipRegion` varchar(15) DEFAULT NULL,
  `ShipPostalCode` varchar(10) DEFAULT NULL,
  `ShipCountry` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `OrderDate` (`OrderDate`),
  KEY `ShippedDate` (`ShippedDate`),
  KEY `ShipPostalCode` (`ShipPostalCode`),
  KEY `FK_Orders_Customers` (`CustomerID`),
  KEY `FK_Orders_Employees` (`EmployeeID`),
  KEY `FK_Orders_Shippers` (`ShipVia`),
  CONSTRAINT `FK_Orders_Customers` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`),
  CONSTRAINT `FK_Orders_Employees` FOREIGN KEY (`EmployeeID`) REFERENCES `employees` (`EmployeeID`),
  CONSTRAINT `FK_Orders_Shippers` FOREIGN KEY (`ShipVia`) REFERENCES `shippers` (`ShipperID`)
)

CREATE TABLE `order details` (
  `OrderID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `UnitPrice` decimal(10,4) NOT NULL DEFAULT '0.0000',
  `Quantity` smallint(2) NOT NULL DEFAULT '1',
  `Discount` decimal(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`OrderID`,`ProductID`),
  KEY `FK_Order_Details_Products` (`ProductID`),
  CONSTRAINT `FK_Order_Details_Orders` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`),
  CONSTRAINT `FK_Order_Details_Products` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`)
 */