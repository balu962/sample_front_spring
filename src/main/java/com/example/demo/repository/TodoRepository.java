package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByOrderByDueDateAsc(); // dueDate 오름차순, 오래된 일정부터 표기
    List<Todo> findAllByOrderByDueDateDesc(); // dueDate 내림차순, 최신 일정부터 표기
}
