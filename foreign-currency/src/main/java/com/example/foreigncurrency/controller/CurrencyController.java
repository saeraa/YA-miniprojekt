package com.example.foreigncurrency.controller;

import com.example.foreigncurrency.model.Price;
import com.example.foreigncurrency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CurrencyController {

	@Autowired
	CurrencyService currencyService;

	@PostMapping("/price")
	private ResponseEntity<String> getPrice(@RequestBody Price price) {
		return new ResponseEntity<String>(currencyService.getPrice(price), HttpStatus.OK);
	}
}