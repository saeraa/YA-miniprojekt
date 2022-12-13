package com.example.commonbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
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

/*     String query = "select
ProductID,
ProductName, CategoryID,
QuantityPerUnit, UnitPrice,
 UnitsInStock from products;";

        if(detailed){
            query = "select
            ProductID,
            ProductName, SupplierID,
            CategoryID, QuantityPerUnit,
            UnitPrice, UnitsInStock,
            UnitsOnOrder, ReorderLevel,
            Discontinued
            from products;";
        }
        */

/*
CREATE TABLE `products` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(40) NOT NULL,
  `SupplierID` int(11) DEFAULT NULL,
  `CategoryID` int(11) DEFAULT NULL,
  `QuantityPerUnit` varchar(20) DEFAULT NULL,
  `UnitPrice` decimal(10,4) DEFAULT '0.0000',
  `UnitsInStock` smallint(2) DEFAULT '0',
  `UnitsOnOrder` smallint(2) DEFAULT '0',
  `ReorderLevel` smallint(2) DEFAULT '0',
  `Discontinued` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ProductID`),
  KEY `ProductName` (`ProductName`),
  KEY `FK_Products_Categories` (`CategoryID`),
  KEY `FK_Products_Suppliers` (`SupplierID`),
  CONSTRAINT `FK_Products_Categories` FOREIGN KEY (`CategoryID`) REFERENCES `categories` (`CategoryID`),
  CONSTRAINT `FK_Products_Suppliers` FOREIGN KEY (`SupplierID`) REFERENCES `suppliers` (`SupplierID`)
)
* */
