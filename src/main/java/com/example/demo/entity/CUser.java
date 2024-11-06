package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String ipAddress;
    private String role;
    @OneToMany(mappedBy = "user")
    private List<Guestbook> guestbooks;
    @OneToMany(mappedBy = "user")
    private List<BComment> Comments;
    @OneToMany(mappedBy = "user")
    private List<BComment> guestbookComments;
}
