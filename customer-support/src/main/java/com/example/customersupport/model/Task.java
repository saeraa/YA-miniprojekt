package com.example.customersupport.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Integer id;

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
}
