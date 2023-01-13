package com.example.commonbackend.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SupportIssue {
	private Integer id;
	private String customerId;
	private Priority priority;
	private String comment;
	private StatusType statusType;

	enum Priority {
		LOW, MEDIUM, HIGH
	}

	enum StatusType {
		PENDING, INPROGRESS, DONE
	}

}

