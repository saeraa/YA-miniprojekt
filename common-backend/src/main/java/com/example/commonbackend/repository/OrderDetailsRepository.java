package com.example.commonbackend.repository;

import com.example.commonbackend.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

    List<OrderDetails> findAllByOrderID(int orderID);

}
