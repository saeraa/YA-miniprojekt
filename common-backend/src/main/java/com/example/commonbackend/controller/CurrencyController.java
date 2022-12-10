package com.example.commonbackend.controller;

import com.example.commonbackend.model.Price;
import com.example.commonbackend.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

	@Autowired
	CurrencyService currencyService;

	@PostMapping("/convertCurrency/{currency}")
	public ResponseEntity<String> getPrice(@PathVariable String currency,
																				 @RequestBody Price price) {
		return currencyService.getPrice(price);
	}
}
