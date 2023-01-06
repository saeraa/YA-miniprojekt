package com.example.commonbackend.repository;

import com.example.commonbackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
//
//    @Modifying
//    @Transactional
//    @Query(value = "insert into customers (CompanyName, CustomerID) values ('hello', 'booya')",
//    nativeQuery = true)
//    void addCustomer(Customer customer);
//
//    @Modifying
//    @Transactional
//    @Query(value = "insert into customers (CompanyName, CustomerID) values (:name, :id)",
//            nativeQuery = true)
//    void addCustom(@Param("name") String name, @Param("id") String id);
}
