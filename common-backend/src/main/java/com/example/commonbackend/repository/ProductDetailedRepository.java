package com.example.commonbackend.repository;

import com.example.commonbackend.model.ProductDetailed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailedRepository extends JpaRepository<ProductDetailed, Integer> {
}
