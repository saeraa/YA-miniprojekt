package com.example.commonbackend.service;

import com.example.commonbackend.model.SupportIssue;
import com.example.commonbackend.repository.CustomerRepository;
import com.github.javafaker.Faker;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class SupportIssueService {

	private final WebClient webClient;
	private final Faker faker;
	private final CustomerRepository customerRepository;

	public SupportIssueService(WebClient webClient, Faker faker, CustomerRepository customerRepository) {
		this.webClient = webClient;
		this.faker = faker;
		this.customerRepository = customerRepository;
	}
	String baseURL = "http://support-issues:8282/api/v1";

	public ResponseEntity<?> getIssuesForCustomer (String customerId) {
		var results = webClient
				.get()
				.uri(baseURL + "/tasks/" + customerId)
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
		var results = webClient
				.get()
				.uri(baseURL + "/tasks")
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<SupportIssue>>() {
				})
				.block();
		return results == null ?
				new ResponseEntity<>("Sorry, no support issues found.", HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(results, HttpStatus.OK);
	}

	public ResponseEntity<?> updateIssue (SupportIssue issue) {
		var results = webClient
				.put()
				.uri(baseURL + "/task")
				.body(Mono.just(issue), SupportIssue.class)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<SupportIssue>() {
				})
				.block();
		return results == null ?
				new ResponseEntity<>("Something went wrong. Did you enter the correct task " +
						"format? \n" + issue,
						HttpStatus.BAD_REQUEST) :
				new ResponseEntity<>(results, HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<?> addIssue (SupportIssue issue) {
		var results = webClient
				.post()
				.uri(baseURL + "/task")
				.body(Mono.just(issue), SupportIssue.class)
				.retrieve()
				.bodyToMono(SupportIssue.class)
				.block();
		return results == null ?
				new ResponseEntity<>("Something went wrong. Did you enter the correct task " +
						"format? \n" + issue,
						HttpStatus.BAD_REQUEST) :
				new ResponseEntity<>(results, HttpStatus.CREATED);
	}

	public ResponseEntity<?> removeIssue (String customerId) {
		var results = webClient
				.delete()
				.uri(baseURL + "/tasks/" + customerId)
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

    public ResponseEntity<?> addDummyData() {

		 List<SupportIssue> supportIssues = IntStream.rangeClosed(1,10)
		         .mapToObj(i -> new SupportIssue(
		            customerRepository.getRandomCustomer().getCustomerId(),
					SupportIssue.Priority.values()[faker.number().numberBetween(0,3)],
					faker.harryPotter().quote(),
					SupportIssue.StatusType.values()[faker.number().numberBetween(0,3)]
		         )).toList();

		 supportIssues.forEach(this::addIssue);

		 return ResponseEntity.ok().body("All good.");
    }
}
