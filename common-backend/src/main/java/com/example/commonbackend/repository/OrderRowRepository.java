package com.example.commonbackend.repository;

import com.example.commonbackend.model.OrderRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRowRepository extends JpaRepository<OrderRow, Integer> {
}
