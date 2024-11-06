package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	private Long id;
    private String content;
    private String ipAddress;
    private String nickname;
    private String temporaryPassword;
    private String role;
    private LocalDateTime createDate;
    private Long postId;
}
