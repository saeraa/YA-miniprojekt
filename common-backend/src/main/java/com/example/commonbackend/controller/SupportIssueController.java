package com.example.commonbackend.controller;

import com.example.commonbackend.model.SupportIssue;
import com.example.commonbackend.service.SupportIssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SupportIssueController {

	final SupportIssueService supportIssueService;

	public SupportIssueController (SupportIssueService supportIssueService) {
		Assert.notNull(supportIssueService, "Support Issue Service may not be null.");
		this.supportIssueService = supportIssueService;
	}

	@GetMapping("/supportissues")
	public ResponseEntity<?> getAllIssues () {
		return supportIssueService.getAllIssues();
	}

	@GetMapping("/supportissues/{customerId}")
	public ResponseEntity<?> getIssuesForCustomer (@PathVariable String customerId) {
		return supportIssueService.getIssuesForCustomer(customerId);
	}

	@PostMapping("/supportissue/{customerId}")
	public ResponseEntity<?> addIssue (@PathVariable String customerId, @RequestBody SupportIssue issue) {
		return supportIssueService.addIssue(issue);
	}

	@PutMapping("/supportissue")
	public ResponseEntity<?> updateIssue (@RequestBody SupportIssue issue) {
		return supportIssueService.updateIssue(issue);
	}

	@DeleteMapping("/supportissue/{customerId}")
	public ResponseEntity<?> removeIssue (@PathVariable String customerId) {
		return supportIssueService.removeIssue(customerId);
	}

	@PostMapping("/supportissue/dummy-data")
	public ResponseEntity<?> addDummyData() {
		return supportIssueService.addDummyData();
	}
}
