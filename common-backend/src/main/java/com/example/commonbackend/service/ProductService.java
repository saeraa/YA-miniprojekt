package com.example.commonbackend.service;

import com.example.commonbackend.model.Product;
import com.example.commonbackend.model.ProductDetailed;
import com.example.commonbackend.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ResponseEntity<?> getAllProducts () {
		List<Product> products = productRepository.findAllProducts();
		return products.size() == 0 ?
				new ResponseEntity<>("Sorry, no products found.", HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(products, HttpStatus.OK);
	}

	public ResponseEntity<?> getAllProductsDetailed () {
		List<ProductDetailed> products = productRepository.findAllDetailedProducts();
		return products.size() == 0 ?
				new ResponseEntity<>("Sorry, no products found.", HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(products, HttpStatus.OK);
	}
}
