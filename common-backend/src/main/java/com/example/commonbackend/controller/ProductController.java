package com.example.commonbackend.controller;

import com.example.commonbackend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProductController {

	private final ProductService productService;

	public ProductController (ProductService productService) {
		Assert.notNull(productService, "Product Service may not be null.");
		this.productService = productService;
	}

	@GetMapping("/products")
	public ResponseEntity<?> getProducts () {
		return productService.getAllProducts();
	}

	@GetMapping("/productsdetailed")
	public ResponseEntity<?> getAllProductsDetailed () {
		return productService.getAllProductsDetailed();
	}
}
