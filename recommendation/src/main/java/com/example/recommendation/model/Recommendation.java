package com.example.recommendation.model;

import javax.persistence.*;

@Entity
public class Recommendation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "RATING")
	private int rating; // restricted to 1-10 ?

	@Column(name = "COMMENT", length = 1000)
	private String comment;

	@Column(name = "PRODUCT_ID")
	private int productId; // this should be connected to an existing productId in the db?

	@Column(name = "CREATED_BY")
	private String email;

	public long getId () {
		return id;
	}

	public void setId (long id) {
		this.id = id;
	}

	public int getRating () {
		return rating;
	}

	public void setRating (int rating) {
		this.rating = rating;
	}

	public String getComment () {
		return comment;
	}

	public void setComment (String comment) {
		this.comment = comment;
	}

	public int getProductId () {
		return productId;
	}

	public void setProductId (int productId) {
		this.productId = productId;
	}

	public String getEmail () {
		return email;
	}

	public void setEmail (String email) {
		this.email = email;
	}
}
