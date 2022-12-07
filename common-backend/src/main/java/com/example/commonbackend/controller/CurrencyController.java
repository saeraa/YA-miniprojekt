package com.example.commonbackend.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

	@PostMapping("/convertCurrency/{currency}")
	public String getPrice(@PathVariable String currency) {
		return "HO HO HO";
	}

	// • Get price for cart in other currency
	//POST /convertCurrency/{currency}
}
