package com.example.commonbackend.repository;

import com.example.commonbackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Modifying
    @Transactional
    @Query(value = "delete from `order details` where OrderID=:orderID", nativeQuery = true)
    int deleteOrderRow(@Param("orderID") Integer orderID);

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO northwind.`order details` (OrderID,
            ProductID) 
            VALUES (:orderID, :productID)\
            """,
        nativeQuery = true)
    int addOrderRow(int orderID, int productID);

}
