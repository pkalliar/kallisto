package com.pankal.task;

import org.springframework.data.domain.Example;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private TaskRepository taskRepository;

	public TaskController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@PostMapping
	public void addTask(@RequestBody Task task) {
		taskRepository.save(task);
	}

	@GetMapping
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	@PutMapping("/{id}")
	public void editTask(@PathVariable long id, @RequestBody Task task) {

		Example<Task> example = Example.of(task);
		Optional<Task> existingTask = taskRepository.findOne(example);
		Assert.notNull(existingTask, "Task not found");
		existingTask.get().setDescription(task.getDescription());
		taskRepository.save(existingTask.get());
	}

//	@DeleteMapping("/{id}")
//	public void deleteTask(@PathVariable long id) {
//		taskRepository.delete(id);
//	}
}
