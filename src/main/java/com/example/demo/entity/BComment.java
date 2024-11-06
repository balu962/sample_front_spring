package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BComment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String content;
    private LocalDateTime createDate;
    private String ipAddress;
    private String nickname;
    private String temporaryPassword;
    private String role;
    @ManyToOne
    private CUser user;
    @ManyToOne
    private Post post;
}
