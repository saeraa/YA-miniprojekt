package com.example.recommendation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Recommendation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "RATING")
	private int rating; // restricted to 1-10 ?

	@Column(name = "COMMENT", length = 1000)
	private String comment;

	@Column(name = "PRODUCT_ID")
	private int productId;

	@Column(name = "CREATED_BY")
	private String email;
}
