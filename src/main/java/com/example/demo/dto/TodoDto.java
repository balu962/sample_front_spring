package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDto {
	private Long id;
    private String subject;
    private String description;
    private LocalDateTime dueDate;
    private boolean complete;
}
