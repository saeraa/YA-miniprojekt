package com.example.commonbackend.service;

import com.example.commonbackend.model.Recommendation;
import com.github.javafaker.Faker;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class RecommendationService {

	private final WebClient webClient;

	private final Faker faker;


	String baseURL = "http://recommendation:8181/api/v1";

	public RecommendationService(WebClient webClient, Faker faker) {
		this.webClient = webClient;
		this.faker = faker;
	}

	public ResponseEntity<?> getRecommendations () {
		var results = webClient
				.get()
				.uri(baseURL + "/recommendations")
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Recommendation>>() {
				})
				.block();
		return results == null ?
				new ResponseEntity<>("Sorry, no recommendations found.", HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(results, HttpStatus.OK);
	}

	public ResponseEntity<?> addRecommendation (Recommendation recommendation) {
		var results = webClient
				.post()
				.uri(baseURL + "/recommendation")
				.body(Mono.just(recommendation), Recommendation.class)
				.retrieve()
				.bodyToMono(Recommendation.class)
				.block();
		return results == null ?
				new ResponseEntity<>("Something went wrong. Did you enter the correct information? \n" + recommendation.toString(),
						HttpStatus.BAD_REQUEST) :
				new ResponseEntity<>(results, HttpStatus.CREATED);
	}

	public ResponseEntity<?> getRecommendation (int productId) {
		var results = webClient
				.get()
				.uri(baseURL + "/recommendations/" + productId)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Recommendation>>() {
				})
				.block();
		return results == null ?
				new ResponseEntity<>(String.format("Sorry, no recommendations found for product with the ID: " +
						"%s.", productId),
						HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(results, HttpStatus.OK);
	}

	public ResponseEntity<?> removeRecommendation (int productId) {
		var results = webClient
				.delete()
				.uri(baseURL + "/recommendation/" + productId)
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

	public ResponseEntity<?> addDummyData() {
		 List<Recommendation> recommendations = IntStream.rangeClosed(1,30)
		         .mapToObj(i -> new Recommendation(
		             faker.internet().emailAddress(),
		                 faker.chuckNorris().fact(),
		                 faker.number().numberBetween(1,77),
		                 faker.number().numberBetween(1,10)
		         )).toList();

		recommendations.forEach(this::addRecommendation);

		return ResponseEntity.ok().body("All good.");
	}
}
