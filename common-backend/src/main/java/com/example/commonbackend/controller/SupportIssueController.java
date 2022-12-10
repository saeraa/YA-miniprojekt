package com.example.commonbackend.controller;

import com.example.commonbackend.model.SupportIssue;
import com.example.commonbackend.service.SupportIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupportIssueController {

	@Autowired
	SupportIssueService supportIssueService;

	// @RequestMapping("/api/v1")
	// @DeleteMapping("/tasks/{customerId}") (@PathVariable int customerId)
	// @PutMapping("/task") (@RequestBody Task task)
	// @PostMapping("/task") (@RequestBody Task task)
	// @GetMapping("/tasks/{customerId}")  (@PathVariable int customerId)
	// @GetMapping("/tasks")

	@GetMapping("/supportissues")
	public List<SupportIssue> getAllIssues() {
		return supportIssueService.getAllIssues();
	}

	@GetMapping("/supportissues/{customerId}")
	public List<SupportIssue> getIssuesForCustomer(@PathVariable int customerId) {
		return supportIssueService.getIssuesForCustomer(customerId);
	}

	@PostMapping("/supportissue/{customerId}")
	public SupportIssue addIssue(@PathVariable int customerId, @RequestBody SupportIssue issue) {
		return null;
	}

	@PutMapping("/supportissue")
	public SupportIssue updateIssue(@RequestBody SupportIssue issue) {
		return supportIssueService.updateIssue(issue);
	}

	@DeleteMapping("/supportissue/{customerId}")
	public ResponseEntity<String> removeIssue(@PathVariable int customerId) {
		return supportIssueService.removeIssue(customerId);
	}



	/*
• Get all support tickets for customer
GET /supportissues/{customerId}
Returns an array of customer support objects
• Add a support issue
POST /supportissue/{customerId}
Provide a customer support object as request object
• Get all support tickets
GET /supportissues
Returns an array of all support objects
 */
}
