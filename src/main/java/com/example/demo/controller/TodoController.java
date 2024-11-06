package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TodoDto;
import com.example.demo.service.TodoService;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/all")
    public List<TodoDto> getAllTodos() {
        return todoService.findAll();
    }

    @PostMapping("/create")
    public TodoDto createTodo(@RequestBody TodoDto todoDto) {
        return todoService.create(todoDto);
    }

    @PostMapping("/modify/{id}")
    public TodoDto modifyTodo(@PathVariable("id") Long id, @RequestBody TodoDto todoDto) {
        return todoService.modify(id, todoDto);
    }
    @PostMapping("complete/{id}")
    public void completeTodo(@PathVariable("id") Long id) {
    	this.todoService.completeChange(id);
    }

    @PostMapping("/delete/{id}")
    public void deleteTodo(@PathVariable("id") Long id) {
        todoService.delete(id);
    }
}
