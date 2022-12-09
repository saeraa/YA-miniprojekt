package com.example.foreigncurrency.service;

import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class CurrencyService {
	public Mono<JsonNode> getPrice ()  {

		String baseurl = "https://sdw-wsrest.ecb.europa.eu/service/data/EXR/M.USD.EUR.SP00.A";
		String restOfTheURL = "?lastNObservations=1&format=jsondata&detail=dataonly";

		WebClient client = WebClient.create(baseurl + restOfTheURL);
		var results = client
											.get()
											.retrieve()
//											.bodyToMono(new ParameterizedTypeReference<List<String>>() {})
//											.block();
											.bodyToMono(String.class)
				 							.subscribe(System.out::println);
		//											.retrieve()
//											.bodyToMono(Object[].class);

//		ObjectMapper objectMapper = new ObjectMapper();
//		JsonNode jsonNode1 = objectMapper.treeToValue(results);
//		String output = jsonNode1.get("0").asText();
		//String output = results.subscribe(result -> result.toString()).toString();
//		Object[] objects = results
//													 .share().block();
//		for (JsonNode string: results) {
//			System.out.println(string.asText());
//		}
		System.out.println(results);
		return null;


	}

	// String json = "{ \"color\" : \"Black\", \"type\" : \"FIAT\" }";
	//JsonNode jsonNode = objectMapper.readTree(json);
	//String color = jsonNode.get("color").asText();
	//// Output: color -> Black

	// https://sdw-wsrest.ecb.europa.eu/service/data/EXR/M.USD.EUR.SP00
	// .A?includeHistory=false&lastNObservations=1&format=jsondata&detail=dataonly

}
