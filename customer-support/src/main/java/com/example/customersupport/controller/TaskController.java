package com.example.customersupport.controller;

import com.example.customersupport.model.Task;
import com.example.customersupport.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping("/tasks")
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	@GetMapping("/tasks/{customerId}")
	public List<Task> getTasksForCustomer(@PathVariable int customerId) {
		var allOfThem = taskRepository.findAll();
		var result = new ArrayList<Task>();
		for (Task task: allOfThem) {
			var id = task.getCustomerId();
			if (id == customerId) {
				result.add(task);
			}
		}
		return result;
	}

	@PostMapping("/task")
	public Task createTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}

	@PutMapping("/task")
	public Task updateTask(@RequestBody Task task) {
		var taskToUpdate = taskRepository.findById(task.getCustomerId()).get();
		taskToUpdate.setComment(task.getComment());
		taskToUpdate.setPriority(task.getPriority());
		taskToUpdate.setStatusType(task.getStatusType());

		return taskToUpdate;
	}

	@DeleteMapping("/tasks/{customerId}")
	public String removeTask(@PathVariable int customerId) {
		taskRepository.deleteById(customerId); // assuming delete by id won't work like this
		return "Task successfully deleted";
	}

}
