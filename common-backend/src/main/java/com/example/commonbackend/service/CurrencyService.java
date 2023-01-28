package com.example.commonbackend.service;

import com.example.commonbackend.model.Price;
import com.example.commonbackend.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

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

	public ResponseEntity<?> getPriceForCartItems(List<Product> products, String currency) {
		double cartTotals = products.stream()
				.mapToDouble(Product::getUnitPrice)
						.reduce(0, Double::sum);

		Price price = new Price();
		price.setCurrency(currency);
		price.setEuroPrice(cartTotals);

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
