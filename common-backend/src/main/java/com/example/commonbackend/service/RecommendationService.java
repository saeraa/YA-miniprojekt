package com.example.commonbackend.service;

import com.example.commonbackend.model.Recommendation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RecommendationService {

	String baseURL = "http://localhost:8181/api/v1";
	WebClient client = WebClient.create(baseURL);

	public ResponseEntity<?> getRecommendations() {
		var results = client
											.get()
											.uri("/recommendations")
											.retrieve()
											.bodyToMono(new ParameterizedTypeReference<List<Recommendation>>() {})
											.block();
		return results == null ?
				new ResponseEntity<>("Sorry, no recommendations found.", HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(results, HttpStatus.OK);
	}

	public ResponseEntity<?> addRecommendation(Recommendation recommendation) {
		var results = client
											.post()
											.uri("/recommendation")
											.body(Mono.just(recommendation), Recommendation.class)
											.retrieve()
											.bodyToMono(Recommendation.class)
											.block();
		return results == null ?
				new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST) :
				new ResponseEntity<>(results, HttpStatus.CREATED);
	}

	public ResponseEntity<?> getRecommendation(int productId) {
		var results = client
											.get()
											.uri("/recommendations/" + productId)
											.retrieve()
											.bodyToMono(new ParameterizedTypeReference<List<Recommendation>>() {})
											.block();
		return results == null ?
				new ResponseEntity<>(String.format("Sorry, no recommendations found for product with the ID: " +
						"%s.", productId),
						HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(results, HttpStatus.OK);
	}

	public ResponseEntity<?> removeRecommendation(int productId) {
		var results = client
											.delete()
											.uri("/recommendation/" + productId)
											.retrieve()
											.bodyToMono(String.class)
											.block();
		return results == null ?
				new ResponseEntity<>(String.format("Sorry, no recommendations found for product with the ID: " +
						"%s.", productId), HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(
						String.format("%s recommendation(s) for the productID: %s were deleted.",
								results, productId), HttpStatus.OK);
	}
}
