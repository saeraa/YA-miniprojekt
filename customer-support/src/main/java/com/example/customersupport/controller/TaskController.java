package com.example.customersupport.controller;

import com.example.customersupport.model.Task;
import com.example.customersupport.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping("/tasks")
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	@GetMapping("/tasks/{customerId}")
	public ResponseEntity<?> getTasksForCustomer(@PathVariable int customerId) {
		var allOfThem = taskRepository.findAll();
		var result = new ArrayList<Task>();
		for (Task task: allOfThem) {
			var id = task.getCustomerId();
			if (id == customerId) {
				result.add(task);
			}
		}
		if (result.size() < 1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tasks not found.") ;
		} else {
			return ResponseEntity.ok().body(result);
		}
	}

	@PostMapping("/task")
	public Task createTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}

	@PutMapping("/task")
	public ResponseEntity<?> updateTask(@RequestBody Task task) {
		var id = task.getId();
		boolean taskExists = taskRepository.findById(id).isPresent();
		if (taskExists) {
			var taskToUpdate = taskRepository.findById(task.getId()).get();
			System.out.println(taskToUpdate);
			taskToUpdate.setComment(task.getComment());
			taskToUpdate.setPriority(task.getPriority());
			taskToUpdate.setStatusType(task.getStatusType());
			taskRepository.save(taskToUpdate);
			return new ResponseEntity<>(taskToUpdate, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Sorry, task not found.", HttpStatus.I_AM_A_TEAPOT);
		}



	}

	@DeleteMapping("/tasks/{customerId}")
	public ResponseEntity<String> removeTask(@PathVariable int customerId) {
		// if no tasks found for customer, return 'no such tasks to delete'
		taskRepository.deleteTaskByCustomerId(customerId);
		return ResponseEntity.ok().body("Task from customer " + customerId + " successfully deleted.");
	}

}
