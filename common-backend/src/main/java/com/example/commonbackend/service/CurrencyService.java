package com.example.commonbackend.service;

import com.example.commonbackend.model.Price;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CurrencyService {

	private final WebClient webClient;

	String baseURL = "http://foreign-currency:8383/api/v1";

	public CurrencyService(WebClient webClient) {
		this.webClient = webClient;
	}

	public ResponseEntity<String> getPrice (Price price) {
		var results = webClient
				.post()
				.uri(baseURL + "/price")
				.body(Mono.just(price), Price.class)
				.retrieve()
				.bodyToMono(String.class)
				.block();

		return ResponseEntity.ok().body(results);
	}
}
