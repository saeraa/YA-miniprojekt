package com.example.commonbackend.service;

import com.example.commonbackend.model.SupportIssue;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class SupportIssueService {

	String baseURL = "http://localhost:8282/api/v1/";
	WebClient client = WebClient.create(baseURL);
	public List<SupportIssue> getIssuesForCustomer (int customerId) {
		//TODO: Implement this
		return null;
	}

	public List<SupportIssue> getAllIssues () {
		//TODO: Implement this
		return null;
	}

	public SupportIssue updateIssue (SupportIssue issue) {
		//TODO: Implement this
		return null;
	}

	public ResponseEntity<String> removeIssue (int customerId) {
		//TODO: Implement this
		return null;
	}
}
