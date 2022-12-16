package com.example.customersupport.service;

import com.example.customersupport.model.Task;
import com.example.customersupport.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public ResponseEntity<?> getTasks () {
		var result = taskRepository.findAll();
		return result.size() == 0 ? new ResponseEntity<>("Sorry, no tasks found.",
				HttpStatus.NOT_FOUND) :
							 new ResponseEntity<>(result, HttpStatus.OK);
	}

	public ResponseEntity<?> getTasksForId (int customerId) {
		var result = taskRepository.getTaskByCustomerId(customerId);
		return result.size() == 0 ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) :
							 new ResponseEntity<>(result, HttpStatus.OK);
	}

	public ResponseEntity<?> createTask (Task task) {
		boolean allOK;

		var comment = task.getComment();
		var id = task.getCustomerId();
		var priority = task.getPriority();
		var statusType = task.getStatusType();

		if (priority == null || statusType == null || comment == null || comment.length() < 1 || id == 0) {
			allOK = false;
		} else {
			allOK = true;
			taskRepository.save(task);
		}

		return allOK ? new ResponseEntity<>(task, HttpStatus.CREATED) :
							 ResponseEntity.badRequest().body("Something went wrong, did you enter the correct " +
																										"body? " + task);
	}

	public ResponseEntity<?> updateTask (Task task) {
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
			return new ResponseEntity<>("Sorry, task not found.", HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> removeTask (int customerId) {
		var result = taskRepository.deleteTaskByCustomerId(customerId);
		String pluralOrNot = result > 1 ? " tasks" : " task";
		return result != 0 ? ResponseEntity
														 .ok()
														 .body(result + pluralOrNot + " from customer with the ID: " + customerId + " " +
																			 "successfully " +
																			 "deleted.")
							 : new ResponseEntity<>("Sorry, no tasks found for customerID: " + customerId,
				HttpStatus.NOT_FOUND);
	}
}
