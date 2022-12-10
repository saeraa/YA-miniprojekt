package com.example.foreigncurrency.service;

import com.example.foreigncurrency.model.Price;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CurrencyService {

	public String getPrice (Price price)  {

		String baseurl = "https://sdw-wsrest.ecb.europa.eu/service/data/EXR/M.USD.EUR.SP00.A";
		String restOfTheURL = "?lastNObservations=1&format=jsondata&detail=dataonly";

		WebClient client = WebClient.create(baseurl + restOfTheURL);
		var results = client
											.get()
											.retrieve()
											.bodyToMono(String.class)
											.block();

		String substring = "\"observations\":{\"0\":[";
		assert results != null;
		int test = results.indexOf(substring);
		String exchangeRate = results.substring(test + 21, test + 26);

		price.setExchangeRate(Double.parseDouble(exchangeRate));
		var calculatedPrice = price.calculatedPrice();

		return calculatedPrice + "";


	}
}
