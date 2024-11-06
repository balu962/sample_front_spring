package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CUser;

public interface UserRepository extends JpaRepository<CUser, Long> {
	CUser findByRole(String role);
    CUser findByUsername(String username);
    CUser findByIpAddress(String ipAddress);
}
