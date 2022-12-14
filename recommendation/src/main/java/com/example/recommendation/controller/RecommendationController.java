package com.example.recommendation.controller;

import com.example.recommendation.model.Recommendation;
import com.example.recommendation.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecommendationController {

	@Autowired
	private RecommendationRepository recommendationRepository;

	@GetMapping("/recommendations")
	public List<Recommendation> getRecommendations() {
		return recommendationRepository.findAll();
	}

	@GetMapping("/recommendations/{productId}")
	public List<Recommendation> getRecommendation(@PathVariable int productId) {
		return recommendationRepository.findAllByProductId(productId);
	}

	@PostMapping("/recommendation")
	public Recommendation addRecommendation(@RequestBody Recommendation recommendation) {
		return recommendationRepository.save(recommendation);
	}

	@DeleteMapping("/recommendation/{productId}")
	public String removeRecommendations(@PathVariable int productId) {
			var removedOrNot = recommendationRepository.deleteAllByProductId(productId);
			if (removedOrNot > 0) {
				return "Recommendations for productId: " + productId + " were deleted.";
			} else {
				return "Something went wrong. Did you enter the correct productId?";
			}
	}
}
