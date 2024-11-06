package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
	private Long id;
    private String title;
    private String content;
    private Long boardId;
    private LocalDateTime createDate;
    
}
