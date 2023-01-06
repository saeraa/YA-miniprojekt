package com.example.commonbackend.repository;

import com.example.commonbackend.model.Product;
import com.example.commonbackend.model.ProductDetailed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select 0 as dtype, ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, Discontinued from products;",
    nativeQuery = true)
    List<Product> findAllProducts();

    @Query(value = "select 1 as dtype, ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued from products;",
    nativeQuery = true)
    List<ProductDetailed> findAllDetailedProducts();
}
