package com.example.customersupport.controller;

import com.example.customersupport.model.Task;
import com.example.customersupport.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

	private final TaskService taskService;

	public TaskController (TaskService taskService) {
		Assert.notNull(taskService, "Task Service may not be null.");
		this.taskService = taskService;
	}

	@GetMapping("/tasks")
	public ResponseEntity<?> getTasks () {
		return taskService.getTasks();
	}

	@GetMapping("/tasks/{customerId}")
	public ResponseEntity<?> getTasksForId (@PathVariable String customerId) {
		return taskService.getTasksForId(customerId);
	}

	@PostMapping("/task")
	public ResponseEntity<?> createTask (@RequestBody Task task) {
		return taskService.createTask(task);
	}

	@PutMapping("/task")
	public ResponseEntity<?> updateTask (@RequestBody Task task) {
		return taskService.updateTask(task);
	}

	@DeleteMapping("/tasks/{customerId}")
	public ResponseEntity<?> removeTask (@PathVariable String customerId) {
		return taskService.removeTask(customerId);
	}

}
