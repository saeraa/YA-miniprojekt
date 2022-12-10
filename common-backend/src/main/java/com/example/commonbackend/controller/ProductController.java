package com.example.commonbackend.controller;

import com.example.commonbackend.model.Product;
import com.example.commonbackend.repository.ProductRepository;
import com.example.commonbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getProducts();
	}

	//@CrossOrigin
	@GetMapping("/productsdetailed")
	public List<Product> getAllProductsDetailed(){
		return productService.getAllProductsDetailed();
	}


	// Get all products
	//GET /products
}
