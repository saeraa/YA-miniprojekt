package com.example.customersupport.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

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

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		Task task = (Task) o;
		return id != null && Objects.equals(id, task.id);
	}

	@Override
	public int hashCode () {
		return getClass().hashCode();
	}
}
