package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TodoDto;
import com.example.demo.entity.Todo;
import com.example.demo.error.NotFoundException;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoService {
	@Autowired
	private TodoRepository todoRepository;

	public List<TodoDto> findAll() {
		return this.todoRepository.findAllByOrderByDueDateDesc().stream().map(this::todoToDto).toList();
	}

	public TodoDto create(TodoDto todoDto) {
		Todo todo = new Todo();
		todo.setSubject(todoDto.getSubject());
		todo.setDescription(todoDto.getDescription());
		todo.setDueDate(todoDto.getDueDate());
		todo.setComplete(todoDto.isComplete());
		Todo newTodo = this.todoRepository.save(todo);
		return todoToDto(newTodo);
	}
	public void delete(Long todoId) {
		this.todoRepository.delete(getOneTodoById(todoId));
	}
	public TodoDto modify(Long todoId, TodoDto todoDto) {
		Todo todo = getOneTodoById(todoId);
		todo.setSubject(todoDto.getSubject());
		todo.setDescription(todoDto.getDescription());
		todo.setDueDate(todoDto.getDueDate());
		todo.setComplete(todoDto.isComplete());
		Todo updatedTodo = this.todoRepository.save(todo);
		return todoToDto(updatedTodo);
	}
	public Todo getOneTodoById(Long todoId) {
		return this.todoRepository.findById(todoId).orElseThrow(
				()-> new NotFoundException("Not Found Todo Id "+todoId));
	}
	public TodoDto todoToDto(Todo todo) {
		TodoDto todoDto = new TodoDto();
		todoDto.setComplete(todo.isComplete());
		todoDto.setDescription(todo.getDescription());
		todoDto.setDueDate(todo.getDueDate());
		todoDto.setSubject(todo.getSubject());
		todoDto.setId(todo.getId());
		return todoDto;
	}
	public void completeChange(Long id) {
		Todo todo = getOneTodoById(id);
		todo.setComplete(!todo.isComplete());
		this.todoRepository.save(todo);
	}
}
