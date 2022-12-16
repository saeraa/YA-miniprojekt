package com.example.customersupport.controller;

import com.example.customersupport.model.Task;
import com.example.customersupport.repository.TaskRepository;
import com.example.customersupport.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/tasks")
	public ResponseEntity<?> getTasks () {
		return taskService.getTasks();
	}

	@GetMapping("/tasks/{customerId}")
	public ResponseEntity<?> getTasksForId (@PathVariable int customerId) {
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
	public ResponseEntity<String> removeTask (@PathVariable int customerId) {
		return taskService.removeTask(customerId);
	}

}
