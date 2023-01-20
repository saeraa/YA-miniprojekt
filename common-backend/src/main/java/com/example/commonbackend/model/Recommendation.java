package com.example.commonbackend.model;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recommendation {
	@Id
	private int id;
	private int rating;
	private String comment;
	private int productId;
	private String email;

	public Recommendation(String emailAddress,
						  String quotes,
						  int numberBetween,
						  int rating) {
		this.comment = quotes;
		this.email = emailAddress;
		this.productId = numberBetween;
		this.rating = rating;
	}
}
