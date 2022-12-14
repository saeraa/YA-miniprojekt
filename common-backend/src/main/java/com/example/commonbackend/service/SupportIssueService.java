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
	public List<SupportIssue> getIssuesForCustomer (int customerId) {
		var results = client
											.get()
											.uri("/tasks/" + customerId)
											.retrieve()
											.bodyToMono(new ParameterizedTypeReference<List<SupportIssue>>() {})
											.block();
		return results;
	}

	public List<SupportIssue> getAllIssues () {
		var results = client
											.get()
											.uri("/tasks")
											.retrieve()
											.bodyToMono(new ParameterizedTypeReference<List<SupportIssue>>() {})
											.block();
		return results;
	}

	public ResponseEntity<?> updateIssue (SupportIssue issue) {
		var results = client
											.put()
											.uri("/task")
											.body(Mono.just(issue), SupportIssue.class)
											.retrieve()
											.bodyToMono(ResponseEntity.class)
											.block();
		return results;
		/*
		If issue is not found,
		 throws Exception: WebClientResponseException: 418 I'm a teapot from PUT
		 How to solve?
		 */
	}

	public SupportIssue addIssue (SupportIssue issue) {
		var results = client
											.post()
											.uri("/task")
											.body(Mono.just(issue), SupportIssue.class)
											.retrieve()
											.bodyToMono(SupportIssue.class)
											.block();
		return results;
	}

	public ResponseEntity<String> removeIssue (int customerId) {
		var results = client
											.delete()
											.uri("/tasks/" + customerId)
											.retrieve()
											.bodyToMono(String.class)
											.block();
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
}
