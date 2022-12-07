package com.example.customersupport.model;

import com.example.customersupport.Priority;
import com.example.customersupport.StatusType;

import javax.persistence.*;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;


	@Column(name = "CUSTOMER")
	private int customerId;

	@Enumerated(EnumType.STRING)
	@Column(name = "PRIORITY")
	private Priority priority;

	@Column(name = "COMMENT", length = 1000)
	private String comment;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private StatusType statusType;


	public int getCustomerId () {
		return customerId;
	}

	public void setCustomerId (int customerId) {
		this.customerId = customerId;
	}

	public Priority getPriority () {
		return priority;
	}

	public void setPriority (Priority priority) {
		this.priority = priority;
	}

	public String getComment () {
		return comment;
	}

	public void setComment (String comment) {
		this.comment = comment;
	}

	public StatusType getStatusType () {
		return statusType;
	}

	public void setStatusType (StatusType statusType) {
		this.statusType = statusType;
	}

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}
}
