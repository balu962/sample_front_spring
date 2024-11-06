package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BComment;

public interface CommentRepository extends JpaRepository<BComment, Long> {
	List<BComment> findByPostIdOrderByIdAsc(Long postId);
}
