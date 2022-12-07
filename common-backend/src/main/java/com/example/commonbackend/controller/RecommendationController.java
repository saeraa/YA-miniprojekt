package com.example.commonbackend.controller;

import com.example.commonbackend.model.Recommendation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendationController {

	@GetMapping("/recommendations")
	public List<Recommendation> getRecommendations() {
		return null;
	}

	@PostMapping("/recommendation")
	public Recommendation addRecommendation(@RequestBody Recommendation recommendation) {
		return null;
	}

	//
//	Provide the list of products as a request object.
//• Get all recommendations
//	GET /recommendations
//	Returns an array of recommendation objects
//• Add a recommendation
//	POST /recommendation
//	Provide a recommendation as request object

	/*
	    @PostMapping("/recommendation")
    public void addRecommendation(@RequestBody Recommendation recommendation) {
        WebClient client = WebClient.create("http://recommendation-service.labnet.io:8001");
        var result = client.post()
                .uri("/recommendation")
                .body(Mono.just(recommendation), Recommendation.class)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
    }

	 */
}
