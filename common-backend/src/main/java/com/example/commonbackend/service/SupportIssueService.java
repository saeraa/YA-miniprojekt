package com.example.commonbackend.service;

import com.example.commonbackend.model.SupportIssue;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SupportIssueService {

	String baseURL = "http://localhost:8282/api/v1";
	WebClient client = WebClient.create(baseURL);

	public ResponseEntity<?> getIssuesForCustomer (String customerId) {
		var results = client
				.get()
				.uri("/tasks/" + customerId)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<SupportIssue>>() {
				})
				.block();
		return results == null ?
				new ResponseEntity<>(String.format("Sorry, no support issues found for customer " +
								"with ID %s.",
						customerId),
						HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(results, HttpStatus.OK);
	}

	public ResponseEntity<?> getAllIssues () {
		var results = client
				.get()
				.uri("/tasks")
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<SupportIssue>>() {
				})
				.block();
		return results == null ?
				new ResponseEntity<>("Sorry, no support issues found.", HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(results, HttpStatus.OK);
	}

	public ResponseEntity<?> updateIssue (SupportIssue issue) {
		var results = client
				.put()
				.uri("/task")
				.body(Mono.just(issue), SupportIssue.class)
				.retrieve()
				.bodyToMono(ResponseEntity.class)
				.block();
		return results == null ?
				new ResponseEntity<>("Something went wrong. Did you enter the correct task " +
						"format? \n" + issue,
						HttpStatus.BAD_REQUEST) :
				new ResponseEntity<>(results, HttpStatus.OK);
	}

	public ResponseEntity<?> addIssue (SupportIssue issue) {
		var results = client
				.post()
				.uri("/task")
				.body(Mono.just(issue), SupportIssue.class)
				.retrieve()
				.bodyToMono(SupportIssue.class)
				.block();
		return results == null ?
				new ResponseEntity<>("Something went wrong. Did you enter the correct task " +
						"format? \n" + issue,
						HttpStatus.BAD_REQUEST) :
				new ResponseEntity<>(results, HttpStatus.OK);
	}

	public ResponseEntity<?> removeIssue (String customerId) {
		var results = client
				.delete()
				.uri("/tasks/" + customerId)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		String pluralOrNot = "";
		if (results != null) {
			pluralOrNot = Integer.parseInt(results) > 1 ? " tasks" : " task";
		}
		return results == null || Integer.parseInt(results) == 0 ?
				new ResponseEntity<>(String.format("Could not find support issues for customer " +
								"with the ID %s",
						customerId), HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(String.format(
						"%s %s from the customer with the ID %s successfully deleted", results,
						pluralOrNot, customerId
				), HttpStatus.OK);
	}
}
