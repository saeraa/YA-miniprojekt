package com.example.commonbackend.service;

import com.example.commonbackend.model.Recommendation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RecommendationService {

	String baseURL = "http://localhost:8181/api/v1/";
	WebClient client = WebClient.create(baseURL);

	public List<Recommendation> getRecommendations() {
		var results = client
											.get()
											.uri("/recommendations")
											.retrieve()
											.bodyToMono(new ParameterizedTypeReference<List<Recommendation>>() {})
											.block();
		return results;
	}

	public Recommendation addRecommendation(Recommendation recommendation) {
		var results = client
											.post()
											.uri("/recommendation")
											.body(Mono.just(recommendation), Recommendation.class)
											.retrieve()
											.bodyToMono(Recommendation.class)
											.block();
		return results;
	}

	public List<Recommendation> getRecommendation(int productId) {
		var results = client
											.get()
											.uri("/recommendations/" + productId)
											.retrieve()
											.bodyToMono(new ParameterizedTypeReference<List<Recommendation>>() {})
											.block();
		return results;
	}

	public String removeRecommendation(int productId) {
		var results = client
											.delete()
											.uri("/recommendation/" + productId)
											.retrieve()
											.bodyToMono(String.class)
											.block();
		return results;
	}
}
