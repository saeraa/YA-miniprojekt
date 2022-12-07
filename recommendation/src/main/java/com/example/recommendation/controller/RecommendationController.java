package com.example.recommendation.controller;

import com.example.recommendation.model.Recommendation;
import com.example.recommendation.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RecommendationController {

	@Autowired
	private RecommendationRepository recommendationRepository;

	@GetMapping("/recommendations")
	public List<Recommendation> getRecommendations() {
		return recommendationRepository.findAll();
	}

	@GetMapping("/recommendations/{productId}")
	public Recommendation getRecommendation(@PathVariable long productId) {
		return recommendationRepository.findById(productId).get();
	}

	@PostMapping("/recommendation")
	public Recommendation addRecommendation(@RequestBody Recommendation recommendation) {
		return recommendationRepository.save(recommendation);
	}

	@DeleteMapping("/recommendation/{productId}")
	public String removeRecommendations(@PathVariable Long productId) {
		List<Recommendation> deletedEntries =
				recommendationRepository.findAllById(Collections.singleton(productId));
		return "Recommendations for: " + productId + " were deleted.";
	}
}
