package com.example.commonbackend.controller;

import com.example.commonbackend.model.SupportIssue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupportIssueController {

	@GetMapping("/supportissues/{customerId}")
	public List<SupportIssue> getIssuesForCustomer(@PathVariable int customerId) {
		return null;
	}

	@PostMapping("/supportissue/{customerId}")
	public SupportIssue addIssue(@PathVariable int customerId, @RequestBody SupportIssue issue) {
		return null;
	}

	@GetMapping("/supportissues")
	public List<SupportIssue> getAllIssues() {
		return null;
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
