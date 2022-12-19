package com.example.recommendation.controller;

import com.example.recommendation.model.Recommendation;
import com.example.recommendation.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecommendationController {

	private final RecommendationRepository recommendationRepository;

	@Autowired
	public RecommendationController (RecommendationRepository recommendationRepository) {
		Assert.notNull(recommendationRepository, "Recommendation Repository must not be null.");
		this.recommendationRepository = recommendationRepository;
	}

	@GetMapping("/recommendations")
	public ResponseEntity<?> getRecommendations() {
		var result = recommendationRepository.findAll();
		return result.size() == 0 ?
				new ResponseEntity<>(null, HttpStatus.OK) :
				new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/recommendations/{productId}")
	public ResponseEntity<?> getRecommendation(@PathVariable int productId) {
		var result = recommendationRepository.findAllByProductId(productId);
		return result.size() == 0 ?
				new ResponseEntity<>(null, HttpStatus.OK) :
				new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/recommendation")
	public ResponseEntity<?> addRecommendation(@RequestBody Recommendation recommendation) {
		try {
			var result = recommendationRepository.save(recommendation);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}

	@DeleteMapping("/recommendation/{productId}")
	public ResponseEntity<?> removeRecommendations(@PathVariable int productId) {
			var result = recommendationRepository.deleteAllByProductId(productId);
			return result > 0 ?
				new ResponseEntity<>(result.toString(), HttpStatus.OK) :
				new ResponseEntity<>(null, HttpStatus.OK);
	}
}
