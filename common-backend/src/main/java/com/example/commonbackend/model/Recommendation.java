package com.example.commonbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Recommendation {
	private int id;
	private int rating;
	private String comment;
	private int productId;
	private String email;
}
