package com.example.commonbackend.controller;

import com.example.commonbackend.model.Price;
import com.example.commonbackend.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CurrencyController {

	@Autowired
	CurrencyService currencyService;

	@PostMapping("/convertCurrency")
	@CrossOrigin
	public ResponseEntity<String> getPrice(@RequestBody Price price) {
		return currencyService.getPrice(price);
	}
}
