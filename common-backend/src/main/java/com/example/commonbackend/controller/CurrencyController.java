package com.example.commonbackend.controller;

import com.example.commonbackend.model.Price;
import com.example.commonbackend.model.Product;
import com.example.commonbackend.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CurrencyController {

	private final CurrencyService currencyService;

	public CurrencyController (CurrencyService currencyService) {
		Assert.notNull(currencyService, "Currency Service may not be null.");
		this.currencyService = currencyService;
	}

	@PostMapping("/convertCurrency")
	public ResponseEntity<String> getPrice (@RequestBody Price price) {
		return currencyService.getPrice(price);
	}

	@PostMapping("/convertCurrency/{currency}")
	public ResponseEntity<?> getPriceForCartItems(@RequestBody List<Product> products, @PathVariable String currency) {
		return currencyService.getPriceForCartItems(products, currency);
	}
}
