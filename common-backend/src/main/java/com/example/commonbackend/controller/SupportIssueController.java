package com.example.commonbackend.controller;

import com.example.commonbackend.model.SupportIssue;
import com.example.commonbackend.service.SupportIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SupportIssueController {

	final SupportIssueService supportIssueService;

	@Autowired
	public SupportIssueController (SupportIssueService supportIssueService) {
		Assert.notNull(supportIssueService, "Support Issue Service may not be null.");
		this.supportIssueService = supportIssueService;
	}

	@GetMapping("/supportissues")
	public ResponseEntity<?> getAllIssues () {
		return supportIssueService.getAllIssues();
	}

	@GetMapping("/supportissues/{customerId}")
	public ResponseEntity<?> getIssuesForCustomer (@PathVariable int customerId) {
		return supportIssueService.getIssuesForCustomer(customerId);
	}

	@PostMapping("/supportissue/{customerId}")
	public ResponseEntity<?> addIssue (@PathVariable int customerId, @RequestBody SupportIssue issue) {
		return supportIssueService.addIssue(issue);
	}

	@PutMapping("/supportissue")
	public ResponseEntity<?> updateIssue (@RequestBody SupportIssue issue) {
		return supportIssueService.updateIssue(issue);
	}

	@DeleteMapping("/supportissue/{customerId}")
	public ResponseEntity<?> removeIssue (@PathVariable int customerId) {
		return supportIssueService.removeIssue(customerId);
	}



	/*
• Get all support tickets for customer
GET /supportissues/{customerId}
Returns an array of customer support objects
• Add a support issue
POST /supportissue/{customerId}   --> TODO: This currently not using customerId pathvariable
Provide a customer support object as request object
• Get all support tickets
GET /supportissues
Returns an array of all support objects
 */
}
