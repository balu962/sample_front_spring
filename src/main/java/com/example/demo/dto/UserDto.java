package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	String username;
	String password;
    String ipAddress;
    String role;
}
