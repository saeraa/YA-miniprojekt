package com.example.customersupport.repository;

import com.example.customersupport.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	@Transactional
	void deleteTaskByCustomerId(int customerId);

}
