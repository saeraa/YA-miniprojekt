package com.example.customersupport.service;

import com.example.customersupport.model.Task;
import com.example.customersupport.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class TaskService {

	private final TaskRepository taskRepository;

	public TaskService (TaskRepository taskRepository) {
		Assert.notNull(taskRepository, "Task Repository may not be null.");
		this.taskRepository = taskRepository;
	}

	public ResponseEntity<?> getTasks () {
		var result = taskRepository.findAll();
		return result.size() == 0 ?
				new ResponseEntity<>(null, HttpStatus.OK) :
				new ResponseEntity<>(result, HttpStatus.OK);
	}

	public ResponseEntity<?> getTasksForId (String customerId) {
		var result = taskRepository.getTaskByCustomerId(customerId);
		return result.size() == 0 ?
				new ResponseEntity<>(null, HttpStatus.OK) :
				new ResponseEntity<>(result, HttpStatus.OK);
	}

	public ResponseEntity<?> createTask (Task task) {
		boolean allOK;

		var comment = task.getComment();
		var id = task.getCustomerId();
		var priority = task.getPriority();
		var statusType = task.getStatusType();

		if (priority == null || statusType == null || comment == null || comment.length() < 1 || id == null) {
			allOK = false;
		} else {
			allOK = true;
			taskRepository.save(task);
		}

		return allOK ? new ResponseEntity<>(task, HttpStatus.CREATED) :
				new ResponseEntity<>(null, HttpStatus.OK);
	}

	public ResponseEntity<?> updateTask(Task task) {
		// TODO: update with existsById
		var id = task.getId();
		boolean taskExists = taskRepository.findById(id).isPresent();
		if (taskExists) {
			var taskToUpdate = taskRepository.findById(task.getId()).get();
			taskToUpdate.setComment(task.getComment());
			taskToUpdate.setPriority(task.getPriority());
			taskToUpdate.setStatusType(task.getStatusType());
			taskRepository.save(taskToUpdate);
			return new ResponseEntity<>(taskToUpdate, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}

	public ResponseEntity<?> removeTask (String customerId) {
		var result = taskRepository.deleteTaskByCustomerId(customerId);
		return result != 0 ?
				new ResponseEntity<>(String.valueOf(result), HttpStatus.OK)
				: new ResponseEntity<>(null, HttpStatus.OK);
	}
}
