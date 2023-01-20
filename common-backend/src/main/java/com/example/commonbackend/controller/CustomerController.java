package com.example.commonbackend.controller;

import com.example.commonbackend.model.Customer;
import com.example.commonbackend.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getCustomers() {
        var results = customerRepository.findAll();
        return results.size() == 0 ?
                new ResponseEntity<>("Sorry, no customers found.", HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/customer")
    public String addCustomer(@Validated @NonNull @RequestBody Customer customer) {
        System.out.println(customer.getCustomerId());
        System.out.println(customer.getCompanyName());
        customerRepository.save(customer);
        return "OK";
    }
}
