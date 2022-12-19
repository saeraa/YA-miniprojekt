package com.example.recommendation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "RECOMMENDATION")
@Getter
@Setter
public class Recommendation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "RATING",
			nullable = false,
			columnDefinition = "ENUM('1','2','3','4','5','6','7','8','9','10')")
	private int rating;

	@Column(name = "COMMENT", length = 1000,
			columnDefinition = "TEXT")
	private String comment;

	@Column(name = "PRODUCT_ID",
			nullable = false)
	private int productId;

	@Column(name = "CREATED_BY",
			columnDefinition = "TEXT")
	private String email;
}
