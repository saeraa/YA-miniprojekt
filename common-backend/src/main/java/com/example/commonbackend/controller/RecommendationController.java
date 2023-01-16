package com.example.commonbackend.controller;

import com.example.commonbackend.model.Recommendation;
import com.example.commonbackend.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RecommendationController {

	final RecommendationService recommendationService;

	public RecommendationController (RecommendationService recommendationService) {
		Assert.notNull(recommendationService, "Recommendation Service may not be null.");
		this.recommendationService = recommendationService;
	}

	@GetMapping("/recommendations")
	public ResponseEntity<?> getRecommendations () {
		return recommendationService.getRecommendations();
	}

	@PostMapping("/recommendation")
	public ResponseEntity<?> addRecommendation (@RequestBody Recommendation recommendation) {
		return recommendationService.addRecommendation(recommendation);
	}

	@GetMapping("/recommendations/{productId}")
	public ResponseEntity<?> getRecommendation (@PathVariable int productId) {
		return recommendationService.getRecommendation(productId);
	}

	@DeleteMapping("/recommendation/{productId}")
	public ResponseEntity<?> removeRecommendations (@PathVariable int productId) {
		return recommendationService.removeRecommendation(productId);
	}

}
