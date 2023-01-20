package com.example.customersupport.repository;

import com.example.customersupport.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Transactional
	int deleteTaskByCustomerId (String customerId);

	List<Task> getTaskByCustomerId (String customerId);

}
