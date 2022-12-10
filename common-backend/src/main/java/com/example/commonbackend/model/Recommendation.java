package com.example.commonbackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recommendation {
	private int id;
	private int rating;
	private String comment;
	private int productId;
	private String email;
}
