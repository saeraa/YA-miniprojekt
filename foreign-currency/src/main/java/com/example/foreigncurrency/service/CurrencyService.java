package com.example.foreigncurrency.service;

import com.example.foreigncurrency.model.Price;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CurrencyService {

	public Double getPrice (Price price) {

		String currency = price.getCurrency();

		String baseurl =
				"https://sdw-wsrest.ecb.europa.eu/service/data/EXR/M." + currency + ".EUR.SP00.A";
		String restOfTheURL = "?lastNObservations=1&format=jsondata&detail=dataonly";

		WebClient client = WebClient.builder()
													 .baseUrl(baseurl + restOfTheURL)
													 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
													 .build();
		String jsonString = client
														.get()
														.retrieve()
														.bodyToMono(String.class)
														.block();

		JSONObject obj = new JSONObject(jsonString);
		JSONArray arr = obj.getJSONArray("dataSets");
		JSONObject obj1 = arr.getJSONObject(0);
		JSONObject obj2 = obj1.getJSONObject("series");
		JSONObject obj3 = obj2.getJSONObject("0:0:0:0:0");
		JSONObject obj4 = obj3.getJSONObject("observations");
		JSONArray arr2 = obj4.getJSONArray("0");
		double exchangeRate = arr2.getDouble(0);
		price.setExchangeRate(exchangeRate);

		return price.calculatedPrice();
	}
}



