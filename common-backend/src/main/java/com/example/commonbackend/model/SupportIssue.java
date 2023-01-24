package com.example.commonbackend.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SupportIssue {
	public SupportIssue(String customerId, Priority priority, String comment, StatusType statusType) {
		this.customerId = customerId;
		this.priority = priority;
		this.comment = comment;
		this.statusType = statusType;
	}

	private Integer id;
	private String customerId;
	private Priority priority;
	private String comment;
	private StatusType statusType;

	public enum Priority {
		LOW, MEDIUM, HIGH
	}

	public enum StatusType {
		PENDING, INPROGRESS, DONE
	}

}

