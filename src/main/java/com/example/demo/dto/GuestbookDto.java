package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestbookDto {
	private Long id;
    private String content;
    private String ipAddress;
    private String nickname;
    private String temporaryPassword;
    private String role;
}
