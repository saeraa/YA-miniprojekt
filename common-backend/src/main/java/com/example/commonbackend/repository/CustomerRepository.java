package com.example.commonbackend.repository;

import com.example.commonbackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query(value = "SELECT * FROM customers ORDER BY rand() LIMIT 1", nativeQuery = true)
    Customer getRandomCustomer();
}
