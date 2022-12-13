package com.example.commonbackend.controller;

import com.example.commonbackend.model.Product;
import com.example.commonbackend.model.ProductDetailed;
import com.example.commonbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/productsdetailed")
	public List<ProductDetailed> getAllProductsDetailed(){
		return productService.getAllProductsDetailed();
	}
}
