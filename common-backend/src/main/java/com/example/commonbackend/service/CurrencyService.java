package com.example.commonbackend.service;

import com.example.commonbackend.model.Price;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CurrencyService {

	String baseURL = "http://localhost:8383";

	public ResponseEntity<String> getPrice(Price price) {
		WebClient client = WebClient.create(baseURL);
		var results = client
											.post()
											.uri("api/v1/price")
											.body(Mono.just(price), Price.class)
											.retrieve()
											.bodyToMono(String.class)
											.block();

		return ResponseEntity.ok().body(results);
	}

}
