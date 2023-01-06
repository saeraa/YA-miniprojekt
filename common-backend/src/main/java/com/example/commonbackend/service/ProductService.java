package com.example.commonbackend.service;

import com.example.commonbackend.model.Product;
import com.example.commonbackend.model.ProductDetailed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

	final JdbcTemplate jdbcTemplate;

	public ProductService (JdbcTemplate jdbcTemplate) {
		Assert.notNull(jdbcTemplate, "JDBCTemplate must not be null!");
		this.jdbcTemplate = jdbcTemplate;
	}

	public ResponseEntity<?> getAllProducts () {
		String query = "select ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, " +
				"UnitsInStock, Discontinued from products;";
		List<Product> products = new ArrayList<>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		for (Map<String, Object> row : rows) {
			Product product = new Product();
			getProductsFromMap(row, product);

			products.add(product);
		}
		return products.size() == 0 ?
				new ResponseEntity<>("Sorry, no products found.", HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(products, HttpStatus.OK);
	}

	private void getProductsFromMap (Map<String, Object> row, Product product) {
		product.setProductID((Integer) row.get("ProductID"));
		product.setProductName((String) row.get("ProductName"));
		product.setCategoryID((Integer) row.get("CategoryID"));
		product.setQuantityPerUnit((String) row.get("QuantityPerUnit"));
		product.setUnitPrice(((BigDecimal) row.get("UnitPrice")).doubleValue());
		product.setUnitsInStock((Integer) row.get("UnitsInStock"));
	}

	public ResponseEntity<?> getAllProductsDetailed () {
		String query = "select ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued from products;";
		List<ProductDetailed> products = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		for (Map<String, Object> row : rows) {
			ProductDetailed product = new ProductDetailed();
			getProductsFromMap(row, product);
			product.setSupplierID((Integer) row.get("SupplierID"));
			product.setUnitsOnOrder((Integer) row.get("UnitsOnOrder"));
			product.setReorderLevel((Integer) row.get("ReorderLevel"));
			product.setDiscontinued((Boolean) row.get("Discontinued"));
			products.add(product);
		}
		return products.size() == 0 ?
				new ResponseEntity<>("Sorry, no support issues found.", HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(products, HttpStatus.OK);
	}
}
