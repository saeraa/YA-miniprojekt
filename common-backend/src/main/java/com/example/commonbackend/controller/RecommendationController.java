package com.example.commonbackend.controller;

import com.example.commonbackend.model.Recommendation;
import com.example.commonbackend.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecommendationController {

	@Autowired
	RecommendationService recommendationService;

	@GetMapping("/recommendations")
	public List<Recommendation> getRecommendations() {
		return recommendationService.getRecommendations();
	}

	@PostMapping("/recommendation")
	public Recommendation addRecommendation(@RequestBody Recommendation recommendation) {
		return recommendationService.addRecommendation(recommendation);
	}

	@GetMapping("/recommendations/{productId}")
	public List<Recommendation> getRecommendation(@PathVariable int productId) {
		return recommendationService.getRecommendation(productId);
	}

	@DeleteMapping("/recommendation/{productId}")
	public String removeRecommendations(@PathVariable int productId) {
		return recommendationService.removeRecommendation(productId);
	}

}
