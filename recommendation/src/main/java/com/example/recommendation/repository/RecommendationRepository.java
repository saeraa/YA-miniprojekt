package com.example.recommendation.repository;

import com.example.recommendation.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {
	@Transactional
	Integer deleteAllByProductId(int productId);

	List<Recommendation> findAllByProductId(int productId);
}
