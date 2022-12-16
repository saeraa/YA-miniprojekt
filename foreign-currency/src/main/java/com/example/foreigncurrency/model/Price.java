package com.example.foreigncurrency.model;

public class Price {
	private double exchangeRate;
	private String currency;
	private double euroPrice;

	public double calculatedPrice() {
		return this.euroPrice * this.exchangeRate;
	}

	public double getExchangeRate () {
		return exchangeRate;
	}

	public void setExchangeRate (double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getCurrency () {
		return currency;
	}

	public void setCurrency (String currency) {
		this.currency = currency;
	}

	public double getEuroPrice () {
		return euroPrice;
	}

	public void setEuroPrice (double euroPrice) {
		this.euroPrice = euroPrice;
	}
}
