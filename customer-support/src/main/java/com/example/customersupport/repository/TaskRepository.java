package com.example.customersupport.repository;

import com.example.customersupport.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	@Transactional
	int deleteTaskByCustomerId (int customerId);

	List<Task> getTaskByCustomerId (int customerId);

}
