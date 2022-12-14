package com.example.commonbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Price {
	private double exchangeRate;
	private String currency;
	private double euroPrice;
}
